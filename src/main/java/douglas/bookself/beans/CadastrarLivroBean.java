package douglas.bookself.beans;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import douglas.bookself.models.Author;
import douglas.bookself.models.Book;
import douglas.bookself.repository.AuthorRepository;
import douglas.bookself.repository.BookRepository;
import douglas.bookself.utils.UploadUtils;

@ManagedBean
@SessionScoped
public class CadastrarLivroBean {
	private Book book;

	private Part coverFile;

	public CadastrarLivroBean() {
		this.book = new Book();
	}

	public String irParaEditarLivro(Book book) {
		if (book == null) return null;

		this.book = book;
		return "cadastrarlivro.jsf";
	}

	public String irParaCadastrarLivro() {
		this.book = new Book();
		return "cadastrarlivro.jsf";
	}

	public String cadastrar() {
		String imageName = UploadUtils.saveCover(this.getCoverFile());

		if (imageName == null) {
			FacesContext
				.getCurrentInstance()
				.addMessage(
					"cover-file",
					new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Erro ao enviar imagem", "")
					);
			return null;
		}

		book.setCover(imageName);

		BookRepository
			.getInstance()
			.criarLivro(book);

		return "index.jsf";
	}

	public Collection<Author> getAllAuthors() {
		return AuthorRepository
			.getInstance()
			.listAll();
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
		return this
			.book
			.getAuthors()
			.stream()
			.map(a -> a.getId().toString())
			.collect(Collectors.toList());
	}

	public void setSelectedAuthors(Collection<String> selectedAuthors) {
		Collection<Long> ids = selectedAuthors
			.stream()
			.map(a -> Long.parseLong(a))
			.collect(Collectors.toList());

		Collection<Author> authors = AuthorRepository
			.getInstance()
			.getWithId(ids);

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
