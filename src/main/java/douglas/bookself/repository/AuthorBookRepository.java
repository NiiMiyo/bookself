package douglas.bookself.repository;

import java.util.ArrayList;
import java.util.Collection;

import douglas.bookself.models.Author;
import douglas.bookself.models.AuthorBook;
import douglas.bookself.models.Book;

public class AuthorBookRepository extends Repository<AuthorBook> {
	private static AuthorBookRepository instance;

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

	@SuppressWarnings("unchecked")
	public Collection<Author> getAuthors(Long bookId) {
		return this
			.getEntityManager()
			.createQuery("SELECT a FROM AuthorBook a WHERE bookId = ?1")
			.setParameter(1, bookId)
			.getResultList();
	}
	public Collection<Author> getAuthors(Book book) { return this.getAuthors(book.getId()); }

	@SuppressWarnings("unchecked")
	public Collection<Book> getBooks(Long authorId) {
		return this
			.getEntityManager()
			.createQuery("SELECT a FROM AuthorBook a WHERE bookId = ?1")
			.setParameter(1, authorId)
			.getResultList();
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

