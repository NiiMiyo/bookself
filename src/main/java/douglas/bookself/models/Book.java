package douglas.bookself.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String title;

	@ManyToMany
	@JoinTable(
		name = "authors_books",
		joinColumns = @JoinColumn(name = "author_id"),
		inverseJoinColumns = @JoinColumn(name = "book_id")
	)
	private Collection<Author> authors;

	@Column(nullable = false)
	private String description;

	public Book() { super(); }

	public long getId() { return this.id; }
	public void setId(long id) { this.id = id; }

	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }

	public Collection<Author> getAuthors() { return this.authors; }
	public void setAuthors(Collection<Author> authors) { this.authors = authors; }

	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }
}
