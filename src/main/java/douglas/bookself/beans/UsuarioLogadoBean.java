package douglas.bookself.beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import douglas.bookself.models.Account;

@ManagedBean
@SessionScoped
public class UsuarioLogadoBean {
	private Account loggedUser;

	public UsuarioLogadoBean() { }

	public Account getLoggedUser() { return loggedUser; }
	public void setLoggedUser(Account loggedUser) { this.loggedUser = loggedUser; }

	public boolean isLogado() { return this.loggedUser != null; }
	public void deslogar() { this.loggedUser = null; }

	public void enviarParaLoginSeDeslogado() throws IOException {
		if (!this.isLogado())
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
	}
}
