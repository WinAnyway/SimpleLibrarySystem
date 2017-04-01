package pl.com.bottega.application;

import pl.com.bottega.model.Book;

public class BookInfo {

    private Book book;
    private String clientName;

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "book=" + book +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
