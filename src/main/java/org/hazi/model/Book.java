package org.hazi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Date;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private String title;

    private Date dop;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "book_store",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    private List<Store> stores;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDop() {
        return dop;
    }

    public void setDop(Date dop) {
        this.dop = dop;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\n\tId: %d\n\tISBN: %s\n\tDop: %s\n",
                title, id, isbn, dop.toString());
    }
}
