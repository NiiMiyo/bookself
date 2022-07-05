package douglas.bookself.repository;

import javax.persistence.EntityManager;

import douglas.bookself.models.Account;
import douglas.bookself.models.Book;
import douglas.bookself.models.Comment;

public class CommentRepository extends Repository {
	public static Comment createOrAlterComment(Account user, Book book, String content) {
		Comment comment = new Comment();

		comment.setAccount(user);
		comment.setBook(book);
		comment.setComment(content);

		EntityManager em = CommentRepository.createEntityManager();

		em.getTransaction().begin();
		comment = em.merge(comment);
		em.getTransaction().commit();

		return comment;
	}

	public static void deleteComment(Long id) {
		EntityManager em = CommentRepository.createEntityManager();

		em.getTransaction().begin();
		em.createQuery("DELETE FROM Comment WHERE id = :id")
			.setParameter("id", id)
			.executeUpdate();
		em.getTransaction().commit();

		em.close();
	}
}
