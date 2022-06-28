package douglas.bookself.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import douglas.bookself.repository.AuthorBookRepository;


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

	public Book() { super(); }

	public long getId() { return this.id; }
	public void setId(long id) { this.id = id; }

	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }

	public Collection<Author> getAuthors() {
		return AuthorBookRepository.getInstance().getAuthors(this.id);
	}

	public String getAuthorsNames() {
		Collection<String> names = this
			.getAuthors()
			.stream()
			.map(a -> a.getName())
			.collect(Collectors.toList());
		
		return String.join(", ", names);
	}

	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }

	public String getCover() { return this.cover; }
	public void setCover(String cover) { this.cover = cover; }
	public String getCoverUrl() {
		return this.getCover() == null
			? "images/noimage.png"
			: "covers/" + this.getCover();
	}

	public Integer getYear() { return year; }
	public void setYear(Integer year) { this.year = year; }
}
