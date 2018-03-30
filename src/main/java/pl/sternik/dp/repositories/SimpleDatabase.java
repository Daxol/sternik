package pl.sternik.dp.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import pl.sternik.dp.entities.Book;
import pl.sternik.dp.entities.BookFactory;
import pl.sternik.dp.entities.Status;
import pl.sternik.dp.exceptions.BookAlreadyExistsException;
import pl.sternik.dp.exceptions.NoSuchBookException;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;


@Repository
@Qualifier("myDatabase")
@Scope("singleton")
@Singleton
public class SimpleDatabase implements BookRepository {


    private List<Book> db = new LinkedList<>();

    public SimpleDatabase() {
    }

    public void setDb(List<Book> db) {
        this.db = db;
    }

    @Override
    public Book create(Book book) throws BookAlreadyExistsException {

        if (book.getId() != null) {

            try {
                db.stream().filter(x -> x.getId().equals(book.getId())).findAny();
            } catch (NullPointerException e) {
                db.add(book);
                return book;
            }
        }

        throw new BookAlreadyExistsException("Book exist");
    }

    @Override
    public void deleteById(Long id) throws NoSuchBookException {
        if (!checkIfBookExist(id)) {
            throw new NoSuchBookException("No book with id: " + id);
        }
        Book book = readById(id);
        db.remove(book);
    }

    @Override
    public Book update(Book book) throws NoSuchBookException {
        if (!checkIfBookExist(book.getId())) {
            throw new NoSuchBookException("No book with id: " + book.getId());
        }
        Book old = readById(book.getId());
        old.setStatus(book.getStatus());
        old.setDescription(book.getDescription());
        old.setTitle(book.getTitle());
        old.setAuthor(book.getAuthor());
        old.setPrice(book.getPrice());

        return old;
    }

    @Override
    public Book readById(Long id) throws NoSuchBookException {
        for (Book book : db) {
            if (book.getId().equals(id)) return book;
        }

        throw new NoSuchBookException("No book with id: " + id);
    }


    @Override
    public List<Book> findAll() {
        return db;
    }

    @Override
    public void initBooks(List<Book> db) {
        System.out.println("INIT @@@@@@@@@@@@@@@@@@@@@@@@@@ OINIT @@@@");
        this.db.addAll(db);
    }

    public void showDb() {
        db.forEach(System.out::println);
    }

    private boolean checkIfBookExist(long id) {

        for (Book book : db) {
            if (book.getId().equals(id)) return true;
        }
        return false;
    }
    private static int counter = 0;

    @PostConstruct
    public void initBooks() {
        if (counter == 1) {


            BookFactory factory = new BookFactory(new Book(), true);

            factory.setAuthor("Robert C. Martin")
                    .setTitle("Clean Code")
                    .setDescription(" A Handbook of Agile Software Craftsmanship")
                    .setStatus(Status.FOR_SALE);
            db.add(factory.getBook());

            BookFactory factory1 = new BookFactory(new Book(), true);
            factory1.setAuthor("Sandro Mancuso");
            factory1.setTitle("Software Craftsman");
            factory1.setDescription(" A Handbook of Agile Software Craftsmanship");
            factory1.setStatus(Status.NEW);
            db.add(factory1.getBook());

            BookFactory factory2 = new BookFactory(new Book(), true);

            factory2.setAuthor("Cay S. Horstmann");
            factory2.setTitle("Java primary");
            factory2.setDescription("Theory about Java language");
            factory2.setStatus(Status.OLD);
            db.add(factory2.getBook());

            BookFactory factory3 = new BookFactory(new Book(), true);
            factory3.setAuthor("Robert C. Martin");
            factory3.setTitle("Clean Code");
            factory3.setDescription(" A Handbook of Agile Software Craftsmanship");
            factory3.setStatus(Status.DUBLET);
            db.add(factory3.getBook());

        } else {
            counter++;
            System.out.println("COUNTER @@@@@@@" + counter);
        }
    }

}
