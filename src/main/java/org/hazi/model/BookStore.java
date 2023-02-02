package org.hazi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book_store")
public class BookStore {

    @EmbeddedId
    private BookStoreId id;
    // other properties and methods
    @ManyToOne
    @MapsId("book_id")
    private Book book;
    @ManyToOne
    @MapsId("store_id")
    private Store store;

    private Long count;

    public BookStoreId getId() {
        return id;
    }

    public void setId(BookStoreId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
