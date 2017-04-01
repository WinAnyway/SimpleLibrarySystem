package pl.com.bottega.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Lending {

    @Id
    @GeneratedValue
    private Long id;
    private Long bookId;
    private String clientName;

    Lending(){}

    public Lending(Long bookId, String clientName) {
        this.bookId = bookId;
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "Lending{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
