package douglas.bookself.repository;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

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

	public Book criarLivro(String title, String description, Collection<Long> authors, String imageName, Integer year) {
		Book book = new Book();

		book.setTitle(title);
		book.setDescription(description);
		book.setCover(imageName);
		book.setYear(year);

		EntityManager em = this.getEntityManager();

		em.getTransaction().begin();
		em.persist(book);
		em.getTransaction().commit();

		AuthorBookRepository.getInstance().criarRelacao(book.getId(), authors);
		return book;
	}

	public Collection<Book> getWithId(Collection<Long> ids) {
		return this
			.listAll()
			.stream()
			.filter(b -> ids.contains( b.getId()) )
			.collect(Collectors.toList());
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
