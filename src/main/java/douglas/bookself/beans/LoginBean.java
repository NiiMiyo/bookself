package douglas.bookself.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import douglas.bookself.models.Account;
import douglas.bookself.repository.UserRepository;

@ManagedBean
public class LoginBean {
	private String username;
	private String password;

	@ManagedProperty("#{usuarioLogadoBean}")
	private UsuarioLogadoBean usuarioLogadoBean;

	public LoginBean() { }

	public String logar() {
		Account account = UserRepository.findWithCredentials(username, password);

		// TODO: mensagem de erro
		if (account == null)
			return null;

		this.usuarioLogadoBean.setLoggedUser(account);
		return "index.jsf";
	}

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public UsuarioLogadoBean getUsuarioLogadoBean() { return usuarioLogadoBean; }
	public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) { this.usuarioLogadoBean = usuarioLogadoBean; }
}
