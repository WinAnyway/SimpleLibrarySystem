package pl.com.bottega.application;

import pl.com.bottega.model.Book;

import java.util.List;

public interface BookRepository {

    void put(Book book);

    void remove(Long bookId);

    List<Book> search(BookQuery bookQuery);

    Book get(Long bookId);

    List<Book> getAllBooks();

    Long countAvailable();

}
