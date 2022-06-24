package douglas.bookself.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy = "authors")
	private Collection<Book> books;

	public Author() { super(); }

	public long getId() { return this.id; }
	public void setId(long id) { this.id = id; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public Collection<Book> getBooks() { return this.books; }
	public void setBooks(Collection<Book> books) { this.books = books; }
}
