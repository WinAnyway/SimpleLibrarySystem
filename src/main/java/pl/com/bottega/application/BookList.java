package pl.com.bottega.application;

import pl.com.bottega.model.Book;

import java.util.List;

public class BookList {
    private List<Book> books;
    private Long availableCount;
    private Long lentCount;

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setAvailableCount(Long availableCount) {
        this.availableCount = availableCount;
    }

    public Long getAvailableCount() {
        return availableCount;
    }

    public void setLentCount(Long lentCount) {
        this.lentCount = lentCount;
    }

    public Long getLentCount() {
        return lentCount;
    }

    @Override
    public String toString() {
        return "BookList{" +
                "books=" + books +
                ", availableCount=" + availableCount +
                ", lentCount=" + lentCount +
                '}';
    }
}
