package douglas.bookself.repository;

import java.util.Collection;

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

		em.getTransaction().begin();
		book = em.merge(book);
		em.getTransaction().commit();

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

		return book;
	}

	//cruD
	public static void deleteBook(Book book) {
		EntityManager em = BookRepository.createEntityManager();

		em.getTransaction().begin();
		
	}

	@SuppressWarnings("unchecked")
	public static Collection<Book> getLastAdded(Integer quantity) {
		EntityManager em = BookRepository.createEntityManager();

		return em.createQuery("SELECT b FROM Book b ORDER BY id DESC")
			.setMaxResults(quantity)
			.getResultList();
	}

	public static Book getRandomBook() {
		EntityManager em = BookRepository.createEntityManager();

		@SuppressWarnings("unchecked")
		Collection<Book> randomOrdered = em.createQuery("SELECT b FROM Book b ORDER BY RANDOM()")
			.setMaxResults(1)
			.getResultList();

		if (randomOrdered.iterator().hasNext())
			return randomOrdered.iterator().next();
		else
			return null;
	}
}
