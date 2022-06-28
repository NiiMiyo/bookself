package douglas.bookself.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HeaderBean {
	private String searchText;

	public void search() {
		// TODO
	}

	public String getSearchText() { return searchText; }
	public void setSearchText(String searchBarContent) { this.searchText = searchBarContent; }
}
