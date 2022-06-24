package douglas.bookself.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;

import douglas.bookself.models.Author;
import douglas.bookself.repository.BookRepository;

@ManagedBean
public class CadastrarLivroBean {
	private String title;
	private String description;

	public CadastrarLivroBean() {
		this.title = "";
		this.description = "";
	}

	public void cadastrar() {
		// TODO: Listar autores na página e associar eles ao livro
		Collection<Author> authors = new ArrayList<>();

		BookRepository.getInstance().criarLivro(this.title, this.description, authors);
	}

	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }

	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }
}
