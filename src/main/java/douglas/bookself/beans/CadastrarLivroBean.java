package douglas.bookself.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import douglas.bookself.models.Author;
import douglas.bookself.repository.AuthorRepository;
import douglas.bookself.repository.BookRepository;
import douglas.bookself.utils.UploadUtils;

@ManagedBean
public class CadastrarLivroBean {
	private String title;
	private String description;
	private Collection<Author> selectedAuthors;
	private Part coverFile;

	public CadastrarLivroBean() {
		this.title = "";
		this.description = "";
		this.selectedAuthors = new ArrayList<>();
	}

	public void cadastrar() {
		System.out.println(this.selectedAuthors);
		String imageName = UploadUtils.saveCover(this.coverFile);

		if (imageName == null) {
			FacesContext
				.getCurrentInstance()
				.addMessage(
					"cover-file",
					new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Erro ao enviar imagem", "")
				);
		}

		BookRepository
			.getInstance()
			.criarLivro(
				this.title,
				this.description,
				this.selectedAuthors,
				imageName
			);
	}

	public Collection<Author> getAllAuthors() {
		return AuthorRepository
			.getInstance()
			.listAll();
	}

	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }

	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }

	public Collection<Author> getSelectedAuthors() { return selectedAuthors; }
	public void setSelectedAuthors(Collection<Author> selectedAuthors) { this.selectedAuthors = selectedAuthors; }

	public Part getCoverFile() { return coverFile; }
	public void setCoverFile(Part coverFile) { this.coverFile = coverFile; }
}
