package douglas.bookself.beans;

import java.util.Collection;

import javax.faces.bean.ManagedBean;

import douglas.bookself.models.Book;
import douglas.bookself.repository.BookRepository;

@ManagedBean
public class HomeBean {
	private Book recomendation;

	public HomeBean() { }

	public Collection<Book> getLastAdded() {
		return BookRepository.getInstance().getLastAdded(5);
	}

	public Book getRecomendation() {
		if (this.recomendation == null) {
			this.recomendation = BookRepository.getInstance().getLivroAleatorio();
		}
		return this.recomendation;
	}
}
