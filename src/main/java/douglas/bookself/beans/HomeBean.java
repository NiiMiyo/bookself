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
		return BookRepository.getLastAdded(5);
	}

	public Book getRecomendation() {
		if (this.recomendation == null)
			this.reloadRecomendation();

		return this.recomendation;
	}

	public void reloadRecomendation() {
		this.recomendation = BookRepository.getRandomBook();
	}
}
