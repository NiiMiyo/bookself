package douglas.bookself.beans;

import java.util.Arrays;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import douglas.bookself.models.Author;
import douglas.bookself.models.Book;
import douglas.bookself.repository.AuthorRepository;

@ManagedBean
@SessionScoped
public class VerAutorBean {
	private Author author;

	public String irParaAutorComId(String authorId) {
		Author author = AuthorRepository
			.getInstance()
			.getWithId(Arrays.asList(Long.parseLong(authorId)))
			.iterator()
			.next();

		return this.irParaAutor(author);
	}

	public String irParaAutor(Author author) {
		if (author == null) return null;

		this.author = author;
		return "verautor.jsf";
	}

	public String deletarAutor() {
		AuthorRepository.getInstance().deletarAutor(author);
		this.author = null;

		return "index.jsf";
	}

	public Author getAuthor() { return author; }
	public String getName() { return this.author.getName(); }
	public String getBiography() { return this.author.getBiography(); }
	public Collection<Book> getBooks() { return this.author.getBooks(); }
}
