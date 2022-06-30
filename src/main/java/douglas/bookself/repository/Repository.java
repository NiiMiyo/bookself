package douglas.bookself.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class Repository {
	public static final String PERSISTENCE_UNITY = "unit";

	protected static EntityManager createEntityManager() {
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNITY).createEntityManager();
	}
}
