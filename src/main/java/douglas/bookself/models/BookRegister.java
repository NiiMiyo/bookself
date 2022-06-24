package douglas.bookself.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BookRegister implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	private Book book;

	@Column(nullable = false)
	private RegisterState state;

	public BookRegister() { super(); }

	public long getId() { return this.id; }
	public void setId(long id) { this.id = id; }

	public Book getBook() { return this.book; }
	public void setBook(Book book) { this.book = book; }

	public RegisterState getState() { return this.state; }
	public void setState(RegisterState state) { this.state = state; }
}
