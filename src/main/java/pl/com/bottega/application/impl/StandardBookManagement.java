package pl.com.bottega.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.application.*;
import pl.com.bottega.model.Book;
import pl.com.bottega.model.CreateBookCommand;

import java.util.List;

@Transactional
public class StandardBookManagement implements BookManagement {

    private BookRepository bookRepository;
    private LendingRepository lendingRepository;

    public StandardBookManagement(BookRepository bookRepository, LendingRepository lendingRepository) {
        this.bookRepository = bookRepository;
        this.lendingRepository = lendingRepository;
    }

    @Override
    public Long add(CreateBookCommand cmd) {
        Book book = new Book(cmd);
        bookRepository.put(book);
        return book.getId();
    }

    @Override
    public void remove(Long bookId) {
        bookRepository.remove(bookId);
    }

    @Override
    public List<Book> search(BookQuery bookQuery) {
        return bookRepository.search(bookQuery);
    }

    @Override
    public BookList listAllBooks() {
        BookList bookList = new BookList();
        List<Book> books = bookRepository.getAllBooks();
        bookList.setBooks(books);
        Long availableCount = bookRepository.countAvailable();
        bookList.setAvailableCount(availableCount);
        bookList.setLentCount(books.size() - availableCount);
        return bookList;
    }

    @Override
    public BookInfo show(Long id) {
        BookInfo bookInfo = new BookInfo();
        Book book = bookRepository.get(id);
        bookInfo.setBook(book);
        if (!book.isAvailable())
            bookInfo.setClientName(lendingRepository.getClientNameOf(id));
        return bookInfo;
    }
}
