package douglas.bookself.beans;

import java.io.IOException;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import douglas.bookself.models.Book;
import douglas.bookself.repository.BookRepository;

@ManagedBean
@RequestScoped
public class PesquisaBean {
	private String query;
	private Collection<Book> resultadosDaPesquisa;

	public PesquisaBean() { }

	public void carregarResultados() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		String query = myRequest.getParameter("query");

		if (query == null)
			query = "";

		this.query = query;
		this.resultadosDaPesquisa = BookRepository.searchFor(query);
	}


	public void irParaBusca(String query) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("busca.jsf?query=" + query);
	}

	public String getQuery() { return query; }
	public void setQuery(String query) { this.query = query; }

	public Collection<Book> getResultadosDaPesquisa() { return resultadosDaPesquisa; }
	public void setResultadosDaPesquisa(Collection<Book> resultadosDaPesquisa) { this.resultadosDaPesquisa = resultadosDaPesquisa; }

	public Integer getQuantityFound() {
		return this.resultadosDaPesquisa.size();
	}
}
