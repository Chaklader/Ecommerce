package Ecommerce.repository;

import Ecommerce.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByCategory(String category);

    List<Book> findByTitleContaining(String title);
}