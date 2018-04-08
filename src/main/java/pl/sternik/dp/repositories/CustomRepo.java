package pl.sternik.dp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sternik.dp.entities.Book;

@Repository
public interface CustomRepo extends JpaRepository<Book, Long> {
}
