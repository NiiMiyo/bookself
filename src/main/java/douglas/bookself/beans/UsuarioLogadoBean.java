package douglas.bookself.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import douglas.bookself.models.Account;

@ManagedBean
@SessionScoped
public class UsuarioLogadoBean {
	private Account loggedUser;

	public UsuarioLogadoBean() { }

	public Account getLoggedUser() { return loggedUser; }
	public void setLoggedUser(Account loggedUser) { this.loggedUser = loggedUser; }
}
