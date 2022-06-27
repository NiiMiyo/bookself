package douglas.bookself.repository;

import java.util.Collection;

import douglas.bookself.models.Author;

public class AuthorRepository extends Repository<Author> {
	private static AuthorRepository instance;

	public Author criarAutor(String name) {
		Author author = new Author();

		author.setName(name);

		this.getEntityManager().getTransaction().begin();
		this.getEntityManager().persist(author);
		this.getEntityManager().getTransaction().commit();

		return author;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Author> listAll() {
		return this
			.getEntityManager()
			.createQuery("SELECT a FROM Author a")
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
}
