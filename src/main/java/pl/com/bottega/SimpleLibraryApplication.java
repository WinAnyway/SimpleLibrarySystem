package pl.com.bottega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.com.bottega.application.BookManagement;
import pl.com.bottega.application.BookQuery;

@SpringBootApplication
public class SimpleLibraryApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SimpleLibraryApplication.class, args);
        BookManagement bookManagement = ctx.getBean(BookManagement.class);
        System.out.println(bookManagement.listAllBooks());
        BookQuery bookQuery = new BookQuery();
        bookQuery.setTitle("Ania");
//        bookQuery.setAuthor("Czesio");
        System.out.println(bookManagement.search(bookQuery));
        System.out.println(bookManagement.show(1L));
    }

}
