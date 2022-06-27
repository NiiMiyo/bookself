package douglas.bookself.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;


@Entity
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(insertable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Book.class)
	@JoinTable(
		name = "author_books",
		joinColumns = { @JoinColumn( name = "author_id" ) },
		inverseJoinColumns = { @JoinColumn( name = "book_id" ) }
	)
	private Collection<Book> books;

	public Author() { super(); }

	public Long getId() { return this.id; }
	public void setId(Long id) { this.id = id; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public Collection<Book> getBooks() { return this.books; }
	public void setBooks(Collection<Book> books) { this.books = books; }
}
