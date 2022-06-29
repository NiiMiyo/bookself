package douglas.bookself.beans;

import java.util.Arrays;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import douglas.bookself.models.Author;
import douglas.bookself.models.Book;
import douglas.bookself.repository.BookRepository;

@ManagedBean
@SessionScoped
public class VerLivroBean {
	private Book book;

	public String irParaLivroComId(String bookId) {
		Book book = BookRepository
			.getInstance()
			.getWithId(Arrays.asList(Long.parseLong(bookId)))
			.iterator()
			.next();

		return this.irParaLivro( book );
	}

	public String irParaLivro(Book book) {
		if (book == null) return null;

		this.book = book;
		return "verlivro.jsf";
	}

	public String validate() {
		return this.book == null
			? "index.jsf"
			: null;
	}

	public String deletarLivro() {
		BookRepository.getInstance().deletarLivro(book);
		this.book = null;

		return "index.jsf";
	}

	public Book getBook() { return this.book; }
	public String getTitle() { return book.getTitle(); }
	public String getAuthorsNames() { return book.getAuthorsNames(); }
	public Collection<Author> getAuthors() { return book.getAuthors(); }
	public String getDescription() { return book.getDescription(); }
	public String getCoverUrl() { return book.getCoverUrl(); }
	public Integer getYear() { return book.getYear(); }
}
