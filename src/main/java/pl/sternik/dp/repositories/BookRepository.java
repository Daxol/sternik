package pl.sternik.dp.repositories;

import pl.sternik.dp.entities.Book;
import pl.sternik.dp.exceptions.BookAlreadyExistsException;
import pl.sternik.dp.exceptions.NoSuchBookException;

import java.util.List;



public interface BookRepository {
    Book create(Book book) throws BookAlreadyExistsException;
    Book readById(Long id) throws NoSuchBookException;
    Book update(Book book) throws NoSuchBookException;
    void deleteById(Long id) throws NoSuchBookException;
    List<Book> findAll();

    void initBooks(List<Book> db);

}