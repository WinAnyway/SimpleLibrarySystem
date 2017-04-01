package pl.com.bottega.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.bottega.application.BookManagement;
import pl.com.bottega.application.BookRepository;
import pl.com.bottega.application.LendingProcess;
import pl.com.bottega.application.LendingRepository;
import pl.com.bottega.application.impl.StandardBookManagement;
import pl.com.bottega.application.impl.StandardLendingProcess;

@Configuration
public class Config {

    @Bean
    public LendingProcess lendingProcess(LendingRepository lendingRepository, BookRepository bookRepository) {
        return new StandardLendingProcess(lendingRepository, bookRepository);
    }

    @Bean
    public BookManagement bookManagement(BookRepository bookRepository, LendingRepository lendingRepository) {
        return new StandardBookManagement(bookRepository, lendingRepository);
    }

    @Bean
    public LendingRepository lendingRepository() {
        return new JPALendingRepository();
    }

    @Bean
    public BookRepository bookRepository() {
        return new JPABookRepository();
    }
}
