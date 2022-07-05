package douglas.bookself.beans;

import javax.faces.bean.ManagedBean;

import douglas.bookself.models.Account;
import douglas.bookself.repository.UserRepository;

@ManagedBean
public class CadastrarUsuarioBean {
	private Account user;

	public CadastrarUsuarioBean() { this.user = new Account(); }
	public String cadastrar() {
		UserRepository.createOrAlterUser(this.user);

		return "index.jsf";
	}


	public Account getUser() { return this.user; }
	public void setUser(Account user) { this.user = user; }
}
