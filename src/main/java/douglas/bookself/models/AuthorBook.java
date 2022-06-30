package douglas.bookself.models;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.*;

import douglas.bookself.repository.AuthorRepository;
import douglas.bookself.repository.BookRepository;

@Entity
@Table(name = "author_books")
public class AuthorBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long authorId;
	private Long bookId;

	public AuthorBook() { super(); }

	public Long getId() { return this.id; }
	public void setId(Long id) { this.id = id; }

	public Long getAuthorId() { return this.authorId; }
	public void setAuthorId(Long authorId) { this.authorId = authorId; }

	public Long getBookId() { return this.bookId; }
	public void setBookId(Long bookId) { this.bookId = bookId; }

	public Author getAuthor() {
		return AuthorRepository
			.getInstance()
			.getWithId(this.authorId);
	}

	public Book getBook() {
		System.out.println(this.bookId);

		return BookRepository
			.getInstance()
			.getWithId(this.bookId);
	}
}
