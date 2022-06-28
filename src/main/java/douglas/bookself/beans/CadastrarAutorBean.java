package douglas.bookself.beans;


import javax.faces.bean.ManagedBean;

import douglas.bookself.repository.AuthorRepository;

@ManagedBean
public class CadastrarAutorBean {
	private String name;
	private String biography;

	public CadastrarAutorBean() {
		this.name = "";
	}
	
	public String cadastrar() {
		AuthorRepository.getInstance().criarAutor(this.name, this.biography);
		return "index.jsf";
	}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getBiography() { return biography; }
	public void setBiography(String biography) { this.biography = biography; }
}
