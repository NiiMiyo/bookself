package douglas.bookself.beans;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import douglas.bookself.models.Book;
import douglas.bookself.repository.BookRepository;

@ManagedBean
@SessionScoped
public class PesquisaBean {
	private String query;
	private Collection<Book> resultadosDaPesquisa;

	public PesquisaBean() { }

	public String irParaBusca(String query) {
		if (query == null || query.equals("")) return null;

		this.query = query;
		this.resultadosDaPesquisa = BookRepository.getInstance().pesquisarLivros(query);

		return "busca.jsf";
	}

	public String getQuery() { return query; }
	public void setQuery(String query) { this.query = query; }

	public Collection<Book> getResultadosDaPesquisa() { return resultadosDaPesquisa; }
	public void setResultadosDaPesquisa(Collection<Book> resultadosDaPesquisa) { this.resultadosDaPesquisa = resultadosDaPesquisa; }

	public Integer getQuantityFound() {
		return this.resultadosDaPesquisa.size();
	}
}
