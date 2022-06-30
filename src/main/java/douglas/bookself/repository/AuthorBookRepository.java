package douglas.bookself.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import douglas.bookself.models.Author;
import douglas.bookself.models.AuthorBook;
import douglas.bookself.models.Book;

public class AuthorBookRepository extends Repository<AuthorBook> {
	private static AuthorBookRepository instance;

	public void deletarRelacoes(Book book) {
		EntityManager em = this.getEntityManager();

		em.getTransaction().begin();
		em.createQuery("DELETE FROM AuthorBook ab WHERE ab.bookId = ?1")
			.setParameter(1, book.getId())
			.executeUpdate();
		em.getTransaction().commit();
	}

	public void deletarRelacoes(Author author) {
		EntityManager em = this.getEntityManager();

		em.getTransaction().begin();
		em.createQuery("DELETE FROM AuthorBook ab WHERE ab.authorId = ?1")
			.setParameter(1, author.getId())
			.executeUpdate();
		em.getTransaction().commit();
	}

	public Collection<AuthorBook> criarRelacao(Long bookId, Collection<Long> authorId) {
		Collection<AuthorBook> registered = new ArrayList<>();

		this.getEntityManager().getTransaction().begin();
		for (Long author : authorId) {
			AuthorBook authorBook = new AuthorBook();

			authorBook.setBookId(bookId);
			authorBook.setAuthorId(author);

			this.getEntityManager().persist(authorBook);

			registered.add(authorBook);
		}
		this.getEntityManager().getTransaction().commit();

		return registered;
	}

	public void criarRelacao(Book book, Collection<Author> authors) {
		Collection<Long> ids = authors.stream().map(a -> a.getId()).collect(Collectors.toList());
		this.criarRelacao(book.getId(), ids);
	}

	@SuppressWarnings("unchecked")
	public Collection<Author> getAuthors(Long bookId) {
		Collection<AuthorBook> authorBooks = this.getEntityManager()
			.createQuery("SELECT a FROM AuthorBook a WHERE bookId = ?1")
			.setParameter(1, bookId)
			.getResultList();
		return authorBooks.stream().map(ab -> ab.getAuthor()).collect(Collectors.toList());
	}
	public Collection<Author> getAuthors(Book book) { return this.getAuthors(book.getId()); }

	@SuppressWarnings("unchecked")
	public Collection<Book> getBooks(Long authorId) {
		Collection<AuthorBook> authorBooks = this
			.getEntityManager()
			.createQuery("SELECT ab FROM AuthorBook ab WHERE authorId = ?1")
			.setParameter(1, authorId)
			.getResultList();

		return authorBooks.stream().map(ab -> ab.getBook()).collect(Collectors.toList());
	}
	public Collection<Book> getBooks(Author author) { return this.getBooks(author.getId()); }

	@SuppressWarnings("unchecked")
	@Override
	public Collection<AuthorBook> listAll() {
		return this
			.getEntityManager()
			.createQuery("SELECT a FROM AuthorBook a")
			.getResultList();
	}

	public static AuthorBookRepository getInstance() { return AuthorBookRepository.getInstance(DEFAULT_PERSISTENCE_UNITY); }
	public static AuthorBookRepository getInstance(String persistenceUnity) {
		if (AuthorBookRepository.instance == null) {
			AuthorBookRepository.instance = new AuthorBookRepository(persistenceUnity);
		}

		return AuthorBookRepository.instance;
	}

	private AuthorBookRepository(String persistenceUnity) { super(persistenceUnity); }
	private AuthorBookRepository() { super(); }
}

