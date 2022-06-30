package douglas.bookself.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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

	public void carregarAutor() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String editAuthorId = myRequest.getParameter("edit");

		boolean err = false;
		try {
			Long id = Long.parseLong(editAuthorId);
			this.author = AuthorRepository.findById(id);

			err = (this.author == null);
		} catch(NumberFormatException e) {
			err = true;
		}

		if (err)
			this.author = new Author();
	}

	public String getName() { return this.author.getName(); }
	public void setName(String name) { this.author.setName(name); }

	public String getBiography() { return this.author.getBiography(); }
	public void setBiography(String biography) { this.author.setBiography(biography); }
}
