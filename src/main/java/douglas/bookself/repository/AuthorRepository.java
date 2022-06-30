package douglas.bookself.repository;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EntityManager;

import douglas.bookself.models.Author;

public class AuthorRepository extends Repository<Author> {
	private static AuthorRepository instance;

	public Author criarAutor(Author author) {
		EntityManager em = this.getEntityManager();

		em.getTransaction().begin();
		author = em.merge(author);
		em.getTransaction().commit();

		return author;
	}

	public Author criarAutor(String name, String biography) {
		Author author = new Author();

		author.setName(name);
		author.setBiography(biography);

		return this.criarAutor(author);
	}

	public Author getWithId(Long id) {
		Collection<Author> authors = this.getWithId( Arrays.asList(id) );

		if (authors.iterator().hasNext())
			return authors.iterator().next();
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<Author> getWithId(Collection<Long> ids) {
		return this
			.getEntityManager()
			.createQuery("SELECT a FROM Author a WHERE id IN ?1 ORDER BY id")
			.setParameter(1, ids)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Author> listAll() {
		return this
			.getEntityManager()
			.createQuery("SELECT a FROM Author a ORDER BY id")
			.getResultList();
	}

	public static AuthorRepository getInstance() { return AuthorRepository.getInstance(DEFAULT_PERSISTENCE_UNITY); }
	public static AuthorRepository getInstance(String persistenceUnity) {
		if (AuthorRepository.instance == null) {
			AuthorRepository.instance = new AuthorRepository(persistenceUnity);
		}

		return AuthorRepository.instance;
	}

	private AuthorRepository(String persistenceUnity) { super(persistenceUnity); }
	private AuthorRepository() { super(); }

	public void deletarAutor(Author author) {
		AuthorBookRepository
			.getInstance()
			.deletarRelacoes(author);
	}
}
