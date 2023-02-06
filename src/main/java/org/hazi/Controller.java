package org.hazi;

import org.hazi.model.*;
import org.hazi.View;
import org.hazi.model.HibernateContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import static org.hazi.model.StateENUM.ACTIVE;

public class Controller implements AutoCloseable {

    private HibernateContext model = new HibernateContext();
    private Scanner sc = new Scanner(System.in);

    @Override
    public void close() throws Exception {
        model.close();
    }


//    public void initAll() {
//
//        Session session = model.getSession();
//        Transaction transaction = session.beginTransaction();
//
//        try {
////            Book b1 = new Book();
////            b1.setTitle("SQL praktikák");
////            b1.setIsbn("1233367898765");
////            b1.setDop(Date.valueOf("1990-01-10"));
////
////            Book b2 = new Book();
////            b2.setTitle("Konyhatündér");
////            b2.setIsbn("1234563898765");
////            b2.setDop(Date.valueOf("1998-12-10"));
////
////            Book b3 = new Book();
////            b3.setTitle("Kertészkedés alapjai");
////            b3.setIsbn("1234564898765");
////            b3.setDop(Date.valueOf("1998-12-10"));
////
////            Author a1 = new Author();
////            a1.setName("Kegimen Eszter");
////            a1.setDob("1994");
////
////            Author a2 = new Author();
////            a2.setName("Perusa Miklós");
////            a2.setDob("1954");
//
////            Store s1 = new Store();
////            s1.setAddress("Budapest Moricz Zsigmond körtér 5");
////            s1.setOwner("Pinterino Milán");
////            s1.setState(ACTIVE);
////
////            Store s2 = new Store();
////            s2.setAddress("Szeged Fő utca 9.");
////            s2.setOwner("Huarty Emilia");
////            s2.setState(ACTIVE);
////
////            Store s3 = new Store();
////            s3.setAddress("Debrecen Petőfi utca 32.");
////            s3.setOwner("Lakati Szilárd");
////            s3.setState(ACTIVE);
//
//
////            a1.setBooks(List.of(b1,b2));
////            a2.setBooks(List.of(b3));
////            b1.setAuthor(a1);
////            b2.setAuthor(a1);
////            b3.setAuthor(a2);
////
////            session.persist(b1);
////            session.persist(b2);
////            session.persist(b3);
////            session.persist(a1);
////            session.persist(a2);
////            session.persist(s1);
////            session.persist(s2);
////            session.persist(s3);
//
//
//            transaction.commit();
//        } catch (Exception e) {
//            System.out.println(e);
//            transaction.rollback();
//        }
//
//    }

    public void listBook() {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<Book> q = session.createQuery("FROM Book", Book.class);
        for (Book p : q.list()) {
            System.out.println(p);
        }

        tx.commit();
        session.close();
    }

    public void addBook(String title, String isbn, Date dop, String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setDop(dop);
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        boolean OK = false;
        Query<Author> q = session.createQuery("FROM Author ", Author.class);
        for (var curAuthor : q.list()) {
            if (curAuthor.getName().equals(author)) {
                book.setAuthor(curAuthor);
                session.persist(book);
                session.flush();
                curAuthor.getBook().add(book);
                session.update(curAuthor);
                tx.commit();
                OK = true;
                return;
            }
        }
        if (!OK) {
            System.out.println("A megadott iro nem letezik, kerlek add meg az adatait. (nev mar nem szukseges)");
            System.out.print("Szuletesi eve: ");
            String dob = sc.nextLine();
            Author newAuthor = addAuthorByBook(session, author, dob);
            book.setAuthor(newAuthor);
            session.persist(book);
            session.flush();
            newAuthor.setBooks(List.of(book));
            session.update(newAuthor);
            tx.commit();
        }
        session.close();
    }

    public void updateBook(Long id, String title, String isbn, Date dop) {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<Book> q = session.createQuery("FROM Book", Book.class);
        for (Book p : q.list()) {
            if (p.getId() == id) {
                p.setTitle(title);
                p.setDop(dop);
                p.setIsbn(isbn);
                session.persist(p);
                tx.commit();
                return;
            }
        }
        session.close();
    }

    public void serachBookTitle(String title) {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<Book> q = session.createQuery("FROM Book", Book.class);
        int count = 0;
        Long id = 0L;
        for (Book p : q.list()) {
            if (p.getTitle().toLowerCase().contains(title) || p.getTitle().contains(title)) {
                System.out.println(p);
                count++;
                id= p.getId();
            }
        }
        tx.commit();
        session.close();
        if (count == 0) {
            System.out.println("A keresett konyv nem talalhato!");
        } else if (count == 1){
            View.updateMenu(sc, id);
        }

    }

    public void serachBookAuthor(String authorName) {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<Author> q = session.createQuery("FROM Author ", Author.class);
        int count = 0;
        Long id = 0L;
        for (Author p : q.list()) {
            if (p.getName().contains(authorName) || p.getName().toLowerCase().contains(authorName)) {
                p.getBook().forEach(System.out::println);
                count++;
                id = p.getId();
            }
        }
        tx.commit();
        session.close();
        if (count == 0) {
            System.out.println("A keresett konyv nem talalhato!");
        } else if (count == 1){
            View.updateMenu(sc, id);
        }
    }

    public void serachBookISBN(String isbn) {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<Book> q = session.createQuery("FROM Book", Book.class);
        int count = 0;
        Long id = 0L;
        for (Book p : q.list()) {
            if (p.getIsbn().contains(isbn) || p.getIsbn().toLowerCase().contains(isbn)) {
                System.out.println(p);
                id = p.getId();
                count++;
            }
        }
        tx.commit();
        session.close();
        if (count == 0) {
            System.out.println("A keresett konyv nem talalhato!");
        } else if (count == 1){
            View.updateMenu(sc, id);
        }
    }

    public void listAuthor() {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<Author> q = session.createQuery("FROM Author", Author.class);
        for (Author p : q.list()) {
            System.out.println(p);
        }

        tx.commit();
        session.close();
    }

    public void addAuthor(String name, String dob) {
        Author author = new Author();
        author.setName(name);
        author.setDob(dob);

        Session session = model.getSession();
        Transaction tx = session.beginTransaction();
        session.persist(author);
        tx.commit();
        session.close();
    }

    public Author addAuthorByBook(Session session, String name, String dob) {
        Author author = new Author();
        author.setName(name);
        author.setDob(dob);
        session.persist(author);
        return author;
    }

    public void updateAuthor(Long id, String name, String dob) {
        Session session = model.getSession();

        Transaction tx = session.beginTransaction();

        Query<Author> q = session.createQuery("FROM Author", Author.class);
        for (Author p : q.list()) {
            if (p.getId() == id) {
                p.setName(name);
                p.setDob(dob);
                session.persist(p);
                tx.commit();
                return;
            }
        }
        session.close();
    }

    public void deleteAuthor(Long id) {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<Author> q = session.createQuery("FROM Author", Author.class);
        for (Author p : q.list()) {
            if (p.getId() == id) {
                session.remove(p);
                tx.commit();
                return;
            }
        }
        session.close();
    }

    public void listStore() {
        Session session = model.getSession();

        Transaction tx = session.beginTransaction();

        Query<Store> q = session.createQuery("FROM Store", Store.class);
        for (Store p : q.list()) {
            System.out.println(p);
        }
        tx.commit();
        session.close();
    }

    public void addStore(String address, String owner) {
        Store store = new Store();
        store.setAddress(address);
        store.setOwner(owner);

        Session session = model.getSession();
        Transaction tx = session.beginTransaction();
        session.persist(store);
        tx.commit();
        session.close();
    }

    public void updateStore(Long id, String address, String owner) {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<Store> q = session.createQuery("FROM Store ", Store.class);
        for (Store p : q.list()) {
            if (p.getId() == id) {
                p.setAddress(address);
                p.setOwner(owner);
                session.persist(p);
                tx.commit();
                return;
            }
        }
        session.close();
    }

    public void listBookStoreLessFive() {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<BookStore> q = session.createQuery("FROM BookStore", BookStore.class);
        for (BookStore p : q.list()) {
            if (p.getCount() < 5 && p.getStore().getState().equals(ACTIVE)) {
                System.out.printf("\n%s:\n\t%s - %d piece\n",
                        p.getBook().getTitle(), p.getStore().getAddress(), p.getCount());
            }
        }
        tx.commit();
        session.close();
    }

    public void listBookStore() {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Query<BookStore> q = session.createQuery("FROM BookStore", BookStore.class);
        for (BookStore p : q.list()) {
            System.out.printf("\n%s:\n\t%s - %d piece\n",
                    p.getBook().getTitle(), p.getStore().getAddress(), p.getCount());

        }
        tx.commit();
        session.close();
    }

    public void limit3() {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();
        Map<String, Long> map = new HashMap<>();

        Query<BookStore> q = session.createQuery("FROM BookStore", BookStore.class);
        for (BookStore p : q.list()) {
            if (p.getCount() < 5 && p.getStore().getState().equals(ACTIVE)) {
                map.put(p.getStore().getAddress(), map.getOrDefault(p.getStore().getAddress(), 0L) + (5 - p.getCount()));
            }
        }

        map = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for (Map.Entry<String, Long> entry : map.entrySet())
            if (entry.getValue() >= 10)
                System.out.println("Store: " + entry.getKey() +
                        "\n\tDeficit: = " + entry.getValue());

        tx.commit();
        session.close();
    }
}
