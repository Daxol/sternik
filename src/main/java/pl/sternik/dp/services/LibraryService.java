package pl.sternik.dp.services;

import pl.sternik.dp.entities.Book;

import java.util.List;
import java.util.Optional;



public interface LibraryService {
    List<Book> findAll();

    List<Book> findAllToSell();

    Optional<Book> findById(Long id);

    Optional<Book> create(Book book);

    Optional<Book> edit(Book book);

    Optional<Boolean> deleteById(Long id);

    List<Book> findLatest3();
}