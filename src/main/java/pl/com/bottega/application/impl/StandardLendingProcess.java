package pl.com.bottega.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.application.BookRepository;
import pl.com.bottega.application.LendingProcess;
import pl.com.bottega.application.LendingRepository;
import pl.com.bottega.model.Lending;

@Transactional
public class StandardLendingProcess implements LendingProcess{

    private LendingRepository lendingRepository;
    private BookRepository bookRepository;

    public StandardLendingProcess(LendingRepository lendingRepository, BookRepository bookRepository) {
        this.lendingRepository = lendingRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void lend(Long bookId, String clientName) {
        lendingRepository.put(new Lending(bookId, clientName));
        bookRepository.get(bookId).lend();
    }
}
