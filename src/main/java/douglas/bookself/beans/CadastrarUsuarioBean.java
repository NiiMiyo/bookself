package douglas.bookself.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import douglas.bookself.models.Account;
import douglas.bookself.repository.UserRepository;

@ManagedBean
public class CadastrarUsuarioBean {
	private Account user;

	public CadastrarUsuarioBean() { this.user = new Account(); }
	public String cadastrar() {
		if ( UserRepository.usernameExists(this.user.getUsername()) ) {
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Username em uso")
			);

			return null;
		}

		UserRepository.createOrAlterUser(this.user);

		return "index.jsf";
	}


	public Account getUser() { return this.user; }
	public void setUser(Account user) { this.user = user; }
}
