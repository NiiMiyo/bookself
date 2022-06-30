package douglas.bookself.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import douglas.bookself.models.Author;
import douglas.bookself.models.Book;
import douglas.bookself.repository.AuthorRepository;
import douglas.bookself.repository.BookRepository;
import douglas.bookself.utils.UploadUtils;

@ManagedBean
@RequestScoped
public class CadastrarLivroBean {
	private Book book;

	private Part coverFile;

	public CadastrarLivroBean() { }

	public void carregarLivro() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String editBookId = myRequest.getParameter("edit");

		boolean err = false;
		try {
			Long id = Long.parseLong(editBookId);
			this.book = BookRepository.findById(id);

			err = (this.book == null);
		} catch(NumberFormatException e) {
			err = true;
		}

		if (err)
			this.book = new Book();
	}

	/*
	public String irParaEditarLivro(Book book) {
		if (book == null) return null;

		this.book = book;
		return "cadastrarlivro.jsf";
	}

	public String irParaCadastrarLivro() {
		this.book = new Book();
		return "cadastrarlivro.jsf";
	}
	*/

	public String cadastrar() {
		book.setCover( UploadUtils.saveCover(this.getCoverFile()) );

		BookRepository.createOrAlterBook(book);

		return "index.jsf";
	}

	public Collection<Author> getAllAuthors() {
		return AuthorRepository.getAllAuthors();
	}

	public Collection<Long> getSelectedAuthorsIds() {
		return this.getSelectedAuthors()
				.stream()
				.map( id -> Long.parseLong(id) )
				.collect(Collectors.toList());
	}

	public String getTitle() { return this.book.getTitle(); }
	public void setTitle(String title) { this.book.setTitle(title); }

	public String getDescription() { return this.book.getDescription(); }
	public void setDescription(String description) { this.book.setDescription(description); }

	public Collection<String> getSelectedAuthors() {
		if (this.book.getAuthors() != null)
			return this.book
				.getAuthors()
				.stream()
				.map(a -> a.getId().toString())
				.collect(Collectors.toList());

		else
			return new ArrayList<>();
	}

	public void setSelectedAuthors(Collection<String> selectedAuthors) {
		Collection<Author> authors = selectedAuthors
			.stream()
			.map(a -> AuthorRepository.findById( Long.parseLong(a) ))
			.collect(Collectors.toList());

		this.book.setAuthors(authors);
	}

	public Part getCoverFile() { return coverFile; }
	public void setCoverFile(Part coverFile) { this.coverFile = coverFile; }
	
	public Integer getYear() { return this.book.getYear(); }
	public void setYear(Integer year) { this.book.setYear(year); }

	public String getCoverFileName() {
		return UploadUtils.getFilename(this.coverFile);
	}
}
