package douglas.bookself.repository;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EntityManager;

import douglas.bookself.models.Author;
import douglas.bookself.models.Book;

public class BookRepository extends Repository<Book> {
	private static BookRepository instance;

	public static BookRepository getInstance() { return BookRepository.getInstance(DEFAULT_PERSISTENCE_UNITY); }
	public static BookRepository getInstance(String persistenceUnity) {
		if (BookRepository.instance == null) {
			BookRepository.instance = new BookRepository(persistenceUnity);
		}

		return BookRepository.instance;
	}

	public Book criarLivro(Book book) {
		EntityManager em = this.getEntityManager();

		em.getTransaction().begin();
		book = em.merge(book);
		em.getTransaction().commit();

		Collection<Author> authors = book.getAuthors();

		AuthorBookRepository.getInstance().deletarRelacoes(book);
		AuthorBookRepository.getInstance().criarRelacao(book, authors);
		return book;
	}

	public Book criarLivro(String title, String description, Collection<Long> authorsIds, String imageName, Integer year) {
		Book book = new Book();

		book.setTitle(title);
		book.setDescription(description);
		book.setCover(imageName);
		book.setYear(year);

		Collection<Author> authors = AuthorRepository.getInstance().getWithId(authorsIds);
		book.setAuthors(authors);

		return this.criarLivro(book);
	}

	public Book getWithId(Long id) {
		Collection<Book> books = this.getWithId( Arrays.asList(id) );

		if (books.iterator().hasNext())
			return books.iterator().next();
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<Book> getWithId(Collection<Long> ids) {
		return this
			.getEntityManager()
			.createQuery("SELECT b FROM Book b WHERE id IN ?1 ORDER BY id")
			.setParameter(1, ids)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Book> getLastAdded(Integer amount) {
		return this
			.getEntityManager()
			.createQuery("SELECT b FROM Book b ORDER BY id DESC")
			.setMaxResults(amount)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Book> listAll() {
		return this
			.getEntityManager()
			.createQuery("SELECT b FROM Book b ORDER BY id")
			.getResultList();
	}

	public Book getLivroAleatorio() {
		return (Book) this
			.getEntityManager()
			.createQuery("SELECT b FROM Book b ORDER BY RANDOM()")
			.setMaxResults(1)
			.getResultList().iterator().next();
	}

	@SuppressWarnings("unchecked")
	public Collection<Book> pesquisarLivros(String query) {
		return this
			.getEntityManager()
			.createQuery("SELECT b "
				+ "FROM Book b LEFT JOIN AuthorBook ab ON b.id = ab.bookId "
				+ "LEFT JOIN Author a ON ab.authorId = a.id "
				+ "WHERE LOWER(b.title) LIKE LOWER(?1) "
				+ "OR LOWER(a.name) LIKE LOWER(?1) "
				+ "ORDER BY b.id")
			.setParameter(1, "%" + query + "%")
			.getResultList();
	}

	public void deletarLivro(Book book) {
		AuthorBookRepository
			.getInstance()
			.deletarRelacoes(book);

		this.getEntityManager().getTransaction().begin();
		this.getEntityManager().remove(book);
		this.getEntityManager().getTransaction().commit();
	}

	private BookRepository(String persistenceUnity) { super(persistenceUnity); }
	private BookRepository() { super(); }
}
