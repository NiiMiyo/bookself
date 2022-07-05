package douglas.bookself.models;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

@Entity
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Book book;

	@ManyToOne
	private Account account;

	@Column( length = 1000 )
	private String comment;

	public Comment() { super(); }

	public Long getId() { return this.id; }
	public void setId(Long id) { this.id = id; }

	public Book getBook() { return this.book; }
	public void setBook(Book book) { this.book = book; }

	public Account getAccount() { return this.account; }
	public void setAccount(Account account) { this.account = account; }

	public String getComment() { return comment; }
	public void setComment(String comment) { this.comment = comment; }
}
