package douglas.bookself.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

	public static Account findWithCredentials(String username, String password) {
		EntityManager em = UserRepository.createEntityManager();

		Account account = null;

		try {
			account = (Account) em.createQuery("SELECT a FROM Account a WHERE username = :username AND password = :password")
				.setParameter("username", username)
				.setParameter("password", password)
				.getSingleResult();
		} catch (NoResultException e) { }

		em.close();
		return account;
	}
}
