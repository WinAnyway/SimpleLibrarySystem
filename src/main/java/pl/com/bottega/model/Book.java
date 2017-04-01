package pl.com.bottega.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Year;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Year year;
    private String author;
    private boolean available;

    Book(){}

    public Book(CreateBookCommand cmd) {
        this.title = cmd.getTitle();
        this.year = cmd.getYear();
        this.author = cmd.getAuthor();
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Year getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void makeUnavailable() {
        this.available = false;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }
}
