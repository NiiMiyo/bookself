package douglas.bookself.repository;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import douglas.bookself.models.Author;
import douglas.bookself.models.Book;

public class BookRepository extends Repository {

	// Crud
	public static Book createBook(String title, String description, String cover, Integer year, Collection<Author> authors) {
		Book book = new Book();

		book.setTitle(title);
		book.setDescription(description);
		book.setCover(cover);
		book.setYear(year);
		book.setAuthors(authors);

		return BookRepository.createOrAlterBook(book);
	}

	// CrUd
	public static Book createOrAlterBook(Book book) {
		EntityManager em = BookRepository.createEntityManager();

		book.setTitle(book.getTitle().trim());
		book.setDescription(book.getDescription().trim());

		em.getTransaction().begin();
		book = em.merge(book);
		em.getTransaction().commit();

		em.close();

		return book;
	}

	// cRud
	public static Book findById(Long id) {
		EntityManager em = BookRepository.createEntityManager();

		Book book = null;

		try {
			book = (Book) em.createQuery("SELECT b FROM Book b WHERE id = :id")
				.setParameter("id", id)
				.getSingleResult();
		} catch (NoResultException e) { }

		em.close();
		return book;
	}

	//cruD
	public static void deleteBook(Book book) {
		EntityManager em = BookRepository.createEntityManager();

		em.getTransaction().begin();
		em.createQuery("DELETE FROM Book WHERE id = :id")
			.setParameter("id", book.getId())
			.executeUpdate();
		em.getTransaction().commit();

		em.close();
	}

	@SuppressWarnings("unchecked")
	public static Collection<Book> getAllBooks() {
		EntityManager em = BookRepository.createEntityManager();

		Collection<Book> results = em.createQuery("SELECT b FROM Book b ORDER BY id")
			.getResultList();

		em.close();
		return results;
	}

	@SuppressWarnings("unchecked")
	public static Collection<Book> getLastAdded(Integer quantity) {
		EntityManager em = BookRepository.createEntityManager();

		Collection<Book> results = em.createQuery("SELECT b FROM Book b ORDER BY id DESC")
			.setMaxResults(quantity)
			.getResultList();

		em.close();
		return results;
	}

	public static Book getRandomBook() {
		EntityManager em = BookRepository.createEntityManager();

		@SuppressWarnings("unchecked")
		Collection<Book> randomOrdered = em
			.createQuery("SELECT b FROM Book b ORDER BY RANDOM()")
			.setMaxResults(1)
			.getResultList();

		Book result;
		if (randomOrdered.iterator().hasNext())
			result = randomOrdered.iterator().next();
		else
			result = null;

		em.close();
		return result;
	}

	public static Collection<Book> searchFor(String query) {
		return BookRepository
			.getAllBooks()
			.stream()
			.filter( b -> 
				b.getTitle().toLowerCase().contains(query.toLowerCase()) // Checks if query is on title
				||
				b.getAuthorsNames().toLowerCase().contains(query.toLowerCase()) // Checks if authors names contains query
			).collect(Collectors.toList());
	}
}
