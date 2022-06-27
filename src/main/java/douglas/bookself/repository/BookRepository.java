package douglas.bookself.repository;

import java.util.Collection;

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

	public Book criarLivro(String title, String description, Collection<Author> authors, String imageName) {
		Book book = new Book();
		
		book.setTitle(title);
		book.setDescription(description);
		book.setAuthors(authors);
		book.setCover(imageName);

		this.getEntityManager().getTransaction().begin();
		this.getEntityManager().persist(book);
		this.getEntityManager().getTransaction().commit();

		return book;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Book> listAll() {
		return this
			.getEntityManager()
			.createQuery("SELECT b FROM Book b")
			.getResultList();
	}

	private BookRepository(String persistenceUnity) { super(persistenceUnity); }
	private BookRepository() { super(); }
}
