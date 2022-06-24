package douglas.bookself.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class Repository {
	private EntityManager entityManager;
	public final static String DEFAULT_PERSISTENCE_UNITY = "unit";

	protected Repository() { this(DEFAULT_PERSISTENCE_UNITY); }
	protected Repository(String persistenceUnity) {
		this.entityManager = Persistence.createEntityManagerFactory(persistenceUnity).createEntityManager();
	}

	public EntityManager getEntityManager() { return entityManager; }
}
