package douglas.bookself.repository;

import java.util.Collection;
import java.util.stream.Collectors;

import douglas.bookself.models.Author;

public class AuthorRepository extends Repository<Author> {
	private static AuthorRepository instance;

	public Author criarAutor(String name, String biography) {
		Author author = new Author();

		author.setName(name);
		author.setBiography(biography);

		this.getEntityManager().getTransaction().begin();
		this.getEntityManager().persist(author);
		this.getEntityManager().getTransaction().commit();

		return author;
	}

	public Collection<Author> getWithId(Collection<Long> ids) {
		return this
			.listAll()
			.stream()
			.filter(a -> ids.contains( a.getId()) )
			.collect(Collectors.toList());
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
