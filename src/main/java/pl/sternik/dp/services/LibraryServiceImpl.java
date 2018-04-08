package pl.sternik.dp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sternik.dp.entities.Book;
import pl.sternik.dp.entities.BookFactory;
import pl.sternik.dp.entities.Status;
import pl.sternik.dp.repositories.BookRepository;
import pl.sternik.dp.repositories.CustomRepo;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private BookRepository books;

    @Autowired
    private CustomRepo repo;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<Book> findAll() {

        return repo.findAll();
    }

    @Override
    public List<Book> findLatest3() {
        List books = new LinkedList();

        List<Book> allBooks = this.repo.findAll();
        for (int i = allBooks.size(); i > allBooks.size() - 3; i--) {
            books.add(allBooks.get(i));
        }

        return books;
    }


    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<Book> findById(Long id) {

        return Optional.of(repo.findOne(id));

    }

    @Override
    public Optional<Book> create(Book book) {

        return Optional.of(repo.save(book));


    }

    @Override
    public Optional<Book> edit(Book book) {

        return Optional.of(repo.save(book));

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {

        repo.delete(id);
        return Optional.of(Boolean.TRUE);

    }

    @Override
    public List<Book> findAllToSell() {
        return repo.findAll().stream()
                .filter(p -> Objects.equals(p.getStatus(), Status.FOR_SALE))
                .collect(Collectors.toList());
    }

    @PostConstruct
    @Transactional(Transactional.TxType.REQUIRED)
    public void initBooks() {


        BookFactory factory = new BookFactory(new Book(), true);

        factory.setAuthor("Robert C. Martin")
                .setTitle("Clean Code")
                .setDescription(" A Handbook of Agile Software Craftsmanship")
                .setStatus(Status.FOR_SALE)
                .setPrice(new BigDecimal(50));
        repo.save(factory.getBook());

        BookFactory factory1 = new BookFactory(new Book(), true);
        factory1.setAuthor("Sandro Mancuso");
        factory1.setTitle("Software Craftsman");
        factory1.setDescription(" A Handbook of Agile Software Craftsmanship");
        factory1.setStatus(Status.NEW).setPrice(new BigDecimal(40));

        repo.save(factory1.getBook());

        BookFactory factory2 = new BookFactory(new Book(), true);

        factory2.setAuthor("Cay S. Horstmann");
        factory2.setTitle("Java primary");
        factory2.setDescription("Theory about Java language");
        factory2.setStatus(Status.OLD).setPrice(new BigDecimal(80));

        repo.save(factory2.getBook());

        BookFactory factory3 = new BookFactory(new Book(), true);
        factory3.setAuthor("Robert C. Martin");
        factory3.setTitle("Clean Code");
        factory3.setDescription(" A Handbook of Agile Software Craftsmanship");
        factory3.setStatus(Status.DUBLET).setPrice(new BigDecimal(50));

        repo.save(factory3.getBook());


    }

}
