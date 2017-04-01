package pl.com.bottega.application;

import pl.com.bottega.model.Book;
import pl.com.bottega.model.CreateBookCommand;

import java.util.List;

public interface BookManagement {

    Long add(CreateBookCommand cmd);

    void remove(Long bookId);

    List<Book> search(BookQuery bookQuery);

    BookList listAllBooks();

    BookInfo show(Long id);

}
