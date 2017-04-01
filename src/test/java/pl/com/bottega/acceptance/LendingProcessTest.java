package pl.com.bottega.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.application.BookManagement;
import pl.com.bottega.application.BookRepository;
import pl.com.bottega.application.LendingProcess;
import pl.com.bottega.application.LendingRepository;
import pl.com.bottega.model.CreateBookCommand;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LendingProcessTest {

    @Autowired
    LendingProcess lendingProcess;

    @Autowired
    LendingRepository lendingRepository;

    @Autowired
    BookManagement bookManagement;

    @Autowired
    BookRepository bookRepository;

    @Test
    @Transactional
    public void shouldLendABook() {
        CreateBookCommand cmd = new CreateBookCommand();
        cmd.setTitle("test");
        cmd.setAuthor("test testowy");
        cmd.setYear(Year.of(2017));

        Long id = bookManagement.add(cmd);
        lendingProcess.lend(id, "Janek Dzbanek");

        assertThat(lendingRepository.getClientNameOf(id)).isEqualTo("Janek Dzbanek");
        assertThat(bookRepository.get(id).isAvailable()).isFalse();
    }

}
