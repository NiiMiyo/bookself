package douglas.bookself.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


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

	@OneToMany(mappedBy = "book")
	private Collection<BookRegister> registers;

	@OneToMany(mappedBy = "book")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Comment> comments;

	@ManyToMany( fetch = FetchType.EAGER )
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

	public Collection<BookRegister> getRegisters() { return registers; }
	public void setRegisters(Collection<BookRegister> registers) { this.registers = registers; }

	public Collection<Comment> getComments() { return comments; }
	public void setComments(Collection<Comment> comments) { this.comments = comments; }
}
