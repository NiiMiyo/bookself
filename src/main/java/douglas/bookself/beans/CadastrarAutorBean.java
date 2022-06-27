package douglas.bookself.beans;


import javax.faces.bean.ManagedBean;

import douglas.bookself.repository.AuthorRepository;

@ManagedBean
public class CadastrarAutorBean {
	private String name;

	public CadastrarAutorBean() {
		this.name = "";
	}
	
	public void cadastrar() {
		AuthorRepository.getInstance().criarAutor(this.name);
	}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
}
