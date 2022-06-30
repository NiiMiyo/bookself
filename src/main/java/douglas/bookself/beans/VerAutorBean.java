package douglas.bookself.beans;

import java.io.IOException;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import douglas.bookself.models.Author;
import douglas.bookself.models.Book;
import douglas.bookself.repository.AuthorRepository;

@ManagedBean
@SessionScoped
public class VerAutorBean {
	private Author author;
	public static final String VIEW_ID_PARAM_NAME = "author";

	public String getViewParamName() {
		return VerAutorBean.VIEW_ID_PARAM_NAME;
	}

	public void carregarAutor() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String idString = myRequest.getParameter(VerAutorBean.VIEW_ID_PARAM_NAME);

		boolean err = false;
		try {
			Long id = Long.parseLong(idString);
			this.author = AuthorRepository.findById(id);

			err = (this.author == null);
		} catch(NumberFormatException e) {
			err = true;
		}

		if (err)
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
	}

	public String deletarAutor() {
		AuthorRepository.deleteAuthor(author);

		this.author = null;
		return "index.jsf";
	}

	public Author getAuthor() { return author; }
	public String getName() { return this.author.getName(); }
	public String getBiography() { return this.author.getBiography(); }
	public Collection<Book> getBooks() { return this.author.getBooks(); }
}
