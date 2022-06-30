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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(length = 500)
	private String biography;

	@ManyToMany(mappedBy = "authors")
	private Collection<Book> books;

	public Author() { super(); }

	public Long getId() { return this.id; }
	public void setId(Long id) { this.id = id; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public String getBiography() { return biography; }
	public void setBiography(String biography) { this.biography = biography; }

	public Collection<Book> getBooks() { return books; }
	public void setBooks(Collection<Book> books) { this.books = books; }
}
