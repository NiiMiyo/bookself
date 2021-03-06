package douglas.bookself.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import douglas.bookself.models.Author;

public class AuthorRepository extends Repository {

	// Crud
	public static Author createAuthor(String name, String biography) {
		Author author = new Author();

		author.setName(name);
		author.setBiography(biography);

		return AuthorRepository.createOrAlterAuthor(author);
	}

	// CrUd
	public static Author createOrAlterAuthor(Author author) {
		EntityManager em = AuthorRepository.createEntityManager();

		// TODO: trim on bean
		author.setName(author.getName().trim());
		author.setBiography(author.getBiography().trim());

		em.getTransaction().begin();
		author = em.merge(author);
		em.getTransaction().commit();

		em.close();

		return author;
	}

	// cRud
	public static Author findById(Long id) {
		EntityManager em = AuthorRepository.createEntityManager();
		Author author = null;

		try {
			author = (Author) em.createQuery("SELECT a FROM Author a WHERE id = :id")
				.setParameter("id", id)
				.getSingleResult();
		} catch (NoResultException e) {
		} finally {
			em.close();
		}

		return author;
	}

	// cruD
	public static void deleteAuthor(Author author) {
		EntityManager em = AuthorRepository.createEntityManager();

		em.getTransaction().begin();
		em.createQuery("DELETE FROM Book WHERE id = :id")
			.setParameter("id", author.getId())
			.executeUpdate();
		em.getTransaction().commit();

		em.close();
	}

	@SuppressWarnings("unchecked")
	public static Collection<Author> getAllAuthors() {
		EntityManager em = AuthorRepository.createEntityManager();

		Collection<Author> results = em.createQuery("SELECT a FROM Author a ORDER BY id")
			.getResultList();

		em.close();
		return results;
	}
}
