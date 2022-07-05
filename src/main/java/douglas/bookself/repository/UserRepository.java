package douglas.bookself.repository;

import javax.persistence.EntityManager;

import douglas.bookself.models.Account;

public class UserRepository extends Repository {
	public static Account createUser(String username, String password) {
		Account user = new Account();

		user.setUsername(username);
		user.setPassword(password);

		return UserRepository.createOrAlterUser(user);
	}

	public static Account createOrAlterUser(Account user) {
		EntityManager em = UserRepository.createEntityManager();

		// TODO: trim on Bean
		user.setUsername(user.getUsername().trim());
		// TODO: Hash password

		em.getTransaction().begin();
		// user = em.merge(user);
		em.persist(user);
		em.getTransaction().commit();
		em.close();

		return user;
	}
}
