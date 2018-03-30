package pl.sternik.dp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sternik.dp.entities.Moneta;

public interface MYMoneRespo extends CrudRepository<Moneta, Long> {
}
