package douglas.bookself.beans;

import java.io.IOException;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import douglas.bookself.models.Author;
import douglas.bookself.models.Book;
import douglas.bookself.repository.BookRepository;

@ManagedBean
@SessionScoped
public class VerLivroBean {
	private Book book;
	public static final String VIEW_ID_PARAM_NAME = "book";

	public String getViewParamName() {
		return VerLivroBean.VIEW_ID_PARAM_NAME;
	}

	public void carregarLivro() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String idString = myRequest.getParameter(VerLivroBean.VIEW_ID_PARAM_NAME);

		boolean err = false;
		try {
			Long id = Long.parseLong(idString);
			this.book = BookRepository.findById(id);

			err = (this.book == null);
		} catch(NumberFormatException e) {
			err = true;
		}

		if (err)
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
	}

	public String deletarLivro() {
		BookRepository.deleteBook(book);

		this.book = null;
		return "index.jsf";
	}

	public Book getBook() { return this.book; }
	public Long getId() { return this.book.getId(); }
	public String getTitle() { return book.getTitle(); }
	public String getAuthorsNames() { return book.getAuthorsNames(); }
	public Collection<Author> getAuthors() { return book.getAuthors(); }
	public String getDescription() { return book.getDescription(); }
	public String getCoverUrl() { return book.getCoverUrl(); }
	public Integer getYear() { return book.getYear(); }
}
