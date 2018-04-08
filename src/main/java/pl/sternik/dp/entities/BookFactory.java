package pl.sternik.dp.entities;

import java.math.BigDecimal;


public class BookFactory {

    private Book book;




    public Book getBook() {
        return book;
    }

    public BookFactory(Book book, boolean newId) {
        this.book = book;

    }

    public BookFactory setDescription(String description) {
        this.book.setDescription(description);
        return this;
    }

    public BookFactory setAuthor(String author) {
        this.book.setAuthor(author);
        return this;

    }

    public BookFactory setPrice(BigDecimal price) {
        this.book.setPrice(price);
        return this;

    }

    public BookFactory setTitle(String title) {
        this.book.setTitle(title);
        return this;

    }

    public BookFactory setStatus(Status status) {
        this.book.setStatus(status);
        return this;

    }
}
