package org.hazi.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BookStoreId implements Serializable {

    private Long book_id;
    private Long store_id;

    // equals and hashCode methods
}
