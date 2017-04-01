package pl.com.bottega.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.application.*;
import pl.com.bottega.model.Book;
import pl.com.bottega.model.CreateBookCommand;

import java.time.Year;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookManagementTest {

    @Autowired
    BookManagement bookManagement;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LendingProcess lendingProcess;

    @Test
    @Transactional
    public void shouldCreateABook() {
        Long id = createBook();

        assertThat(bookRepository.get(id).getTitle()).isEqualTo("test");
        assertThat(bookRepository.get(id).getAuthor()).isEqualTo("test testowy");
        assertThat(bookRepository.get(id).getYear()).isEqualTo(Year.of(2017));
    }

    @Test
    public void shouldRemoveABook() {
        Long id = createBook();

        bookManagement.remove(id);

        assertThat(bookRepository.get(id)).isNull();
    }

    @Test
    @Transactional
    public void shouldListAllBooks() {
        createBook();
        createBook();

        BookList bookList = bookManagement.listAllBooks();

        assertThat(bookList.getBooks().size()).isEqualTo(2);
        assertThat(bookList.getBooks().get(0).getTitle()).isEqualTo("test");
        assertThat(bookList.getBooks().get(1).getTitle()).isEqualTo("test");
        assertThat(bookList.getAvailableCount()).isEqualTo(2);
        assertThat(bookList.getLentCount()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void shouldShowAvailableBookInfo() {
        Long id = createBook();

        BookInfo bookInfo = bookManagement.show(id);

        assertThat(bookInfo.getBook().getId()).isEqualTo(id);
        assertThat(bookInfo.getClientName()).isNull();
    }

    @Test
    @Transactional
    public void shouldShouldShowLentBookInfo() {
        Long id = createBook();
        lendingProcess.lend(id, "Janek Dzbanek");

        BookInfo bookInfo = bookManagement.show(id);

        assertThat(bookInfo.getBook().getId()).isEqualTo(id);
        assertThat(bookInfo.getClientName()).isEqualTo("Janek Dzbanek");
    }

    @Test
    @Transactional
    public void shouldSearchByTitle() {
        createBook();
        createBook();
        createAnotherBook();
        BookQuery query = new BookQuery();
        query.setTitle("test");

        List<Book> books = bookManagement.search(query);

        assertThat(books.size()).isEqualTo(3);
        assertThat(books.get(0).getTitle()).contains("test");
    }

    @Test
    @Transactional
    public void shouldSearchByAuthor() {
        createBook();
        createBook();
        createAnotherBook();
        BookQuery query = new BookQuery();
        query.setAuthor("testowy");

        List<Book> books = bookManagement.search(query);

        assertThat(books.size()).isEqualTo(2);
        assertThat(books.get(0).getAuthor()).contains("testowy");
    }

    @Test
    @Transactional
    public void shouldSearchByYear() {
        createBook();
        createBook();
        createAnotherBook();
        BookQuery query = new BookQuery();
        query.setYear(Year.of(2017));

        List<Book> books = bookManagement.search(query);

        assertThat(books.size()).isEqualTo(2);
        assertThat(books.get(0).getYear()).isEqualTo(Year.of(2017));
    }

    @Test
    @Transactional
    public void shouldSearchByAllCriteria() {
        createBook();
        createBook();
        BookQuery query = new BookQuery();
        query.setTitle("test");
        query.setAuthor("testowy");
        query.setYear(Year.of(2017));

        List<Book> books = bookManagement.search(query);

        assertThat(books.size()).isEqualTo(2);
        assertThat(books.get(0).getTitle()).contains("test");
        assertThat(books.get(0).getAuthor()).contains("testowy");
        assertThat(books.get(0).getYear()).isEqualTo(Year.of(2017));
    }

    private Long createBook() {
        CreateBookCommand cmd = new CreateBookCommand();
        cmd.setTitle("test");
        cmd.setAuthor("test testowy");
        cmd.setYear(Year.of(2017));

        return bookManagement.add(cmd);
    }

    private Long createAnotherBook() {
        CreateBookCommand cmd = new CreateBookCommand();
        cmd.setTitle("wcale nie test");
        cmd.setAuthor("inny autor");
        cmd.setYear(Year.of(1720));

        return bookManagement.add(cmd);
    }

}
