package douglas.bookself.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String title;

	@ManyToMany(mappedBy = "books", cascade = CascadeType.ALL, targetEntity = Author.class)
	private Collection<Author> authors;

	private String description;

	private String cover;

	public Book() { super(); }

	public long getId() { return this.id; }
	public void setId(long id) { this.id = id; }

	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }

	public Collection<Author> getAuthors() { return this.authors; }
	public void setAuthors(Collection<Author> authors) { this.authors = authors; }

	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }

	public String getCover() {
		return cover == null
			? "noimage.png"
			: cover;
	}
	public void setCover(String cover) { this.cover = cover; }
}
