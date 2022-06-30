package douglas.bookself.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import douglas.bookself.models.Author;
import douglas.bookself.repository.AuthorRepository;

@ManagedBean
@SessionScoped
public class CadastrarAutorBean {
	private Author author;

	public CadastrarAutorBean() { }
	
	public String cadastrar() {
		AuthorRepository.createOrAlterAuthor(author);

		return "index.jsf";
	}

	public String irParaCadastrarAutor() {
		this.author = new Author();

		return "cadastrarautor.jsf";
	}

	public String irParaEditarAutor(Author author) {
		if (author == null) return null;

		this.author = author;
		return "cadastrarautor.jsf";
	}

	public String getName() { return this.author.getName(); }
	public void setName(String name) { this.author.setName(name); }

	public String getBiography() { return this.author.getBiography(); }
	public void setBiography(String biography) { this.author.setBiography(biography); }
}
