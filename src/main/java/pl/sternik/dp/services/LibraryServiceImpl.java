package pl.sternik.dp.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sternik.dp.entities.Book;
import pl.sternik.dp.entities.Status;
import pl.sternik.dp.exceptions.BookAlreadyExistsException;
import pl.sternik.dp.repositories.BookRepository;
import pl.sternik.dp.exceptions.NoSuchBookException;


@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private BookRepository books;

    @Override
    public List<Book> findAll() {
        return books.findAll();
    }

    @Override
    public List<Book> findLatest3() {
        List books = new LinkedList();

        List<Book> allBooks = this.books.findAll();
        for (int i = allBooks.size(); i > allBooks.size() - 3; i--) {
            books.add(allBooks.get(i));
        }

        return books;
    }


    @Override
    public Optional<Book> findById(Long id) {
        try {
            return Optional.of(books.readById(id));
        } catch (NoSuchBookException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> create(Book book) {
        try {
            return Optional.of(books.create(book));
        } catch (BookAlreadyExistsException e) {
            try {
                return Optional.of(books.readById(book.getId()));
            } catch (NoSuchBookException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Book> edit(Book book) {
        try {
            return Optional.of(books.update(book));
        } catch (NoSuchBookException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            books.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchBookException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Book> findAllToSell() {
        return books.findAll().stream()
                .filter(p -> Objects.equals(p.getStatus(), Status.FOR_SALE))
                .collect(Collectors.toList());
    }

}
