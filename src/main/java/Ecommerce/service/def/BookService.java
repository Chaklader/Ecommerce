package Ecommerce.service.def;

import Ecommerce.entities.Book;
import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
public interface BookService {

	List<Book> findAll ();

    Book findOne(Long id);
	
	List<Book> findByCategory(String category);
	
	List<Book> blurrySearch(String title);
}