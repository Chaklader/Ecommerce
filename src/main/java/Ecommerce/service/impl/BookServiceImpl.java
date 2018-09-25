package Ecommerce.service.impl;

import Ecommerce.entities.Book;
import Ecommerce.repository.BookRepository;
import Ecommerce.service.def.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    /*
    * the methods to find all the books from the database
    * */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Book> findAll() {

        List<Book> bookList = (List<Book>) bookRepository.findAll();
        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }

        return activeBookList;
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Book> findByCategory(String category) {
        List<Book> bookList = bookRepository.findByCategory(category);

        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }

        return activeBookList;
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Book> blurrySearch(String title) {

        List<Book> bookList = bookRepository.findByTitleContaining(title);
        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }

        return activeBookList;
    }
}
