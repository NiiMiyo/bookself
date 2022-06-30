package douglas.bookself.beans;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import douglas.bookself.models.Author;
import douglas.bookself.models.Book;
import douglas.bookself.repository.BookRepository;

@ManagedBean
@RequestScoped
public class VerLivroBean {
	private Book book;

	public String carregarLivro() {
		System.out.println("TO CARREGANDO");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String idString = myRequest.getParameter("book_id");

		boolean err = false;
		try {
			Long id = Long.parseLong(idString);
			this.book = BookRepository.findById(id);

			err = (this.book == null);
		} catch(NumberFormatException e) {
			err = true;
		}

		return err
			? "index.jsf"
			: null;
	}

	public String deletarLivro() {
		BookRepository.deleteBook(book);

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
