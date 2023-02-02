package org.hazi.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String owner;

    @Enumerated(EnumType.STRING)
    private StateENUM state;

    @ManyToMany(mappedBy = "stores")
    private List<Book> books;

    public Store() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public StateENUM getState() {
        return state;
    }

    public void setState(StateENUM state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("Address: %s\n\tId: %d\n\tOwner: %s\n\tState: %s\n",
                address, id, owner, state);
    }
}
