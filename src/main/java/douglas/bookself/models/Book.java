package douglas.bookself.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String title;

	@Column(length = 500)
	private String description;

	private String cover;

	private Integer year;

	@ManyToMany
	@JoinTable(
		joinColumns = @JoinColumn(name = "book_id"),
		inverseJoinColumns = @JoinColumn(name = "author_id")
	)
	private Collection<Author> authors;

	public Book() { super(); }

	public long getId() { return this.id; }
	public void setId(long id) { this.id = id; }

	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }

	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }

	public String getCover() { return this.cover; }
	public void setCover(String cover) { this.cover = cover; }
	public String getCoverUrl() {
		return this.getCover() == null
			? "resources/images/noimage.png"
			: "covers/" + this.getCover();
	}

	public Integer getYear() { return year; }
	public void setYear(Integer year) { this.year = year; }

	public Collection<Author> getAuthors() { return authors; }
	public void setAuthors(Collection<Author> authors) { this.authors = authors; }
	public String getAuthorsNames() {
		Collection<String> names = this.authors
			.stream()
			.map(a -> a.getName())
			.collect(Collectors.toList());

		return String.join(", ", names);
	}
}
