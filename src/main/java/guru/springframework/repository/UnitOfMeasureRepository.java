package guru.springframework.repository;

import guru.springframework.domain.UnitOfMeashure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeashure, Long> {
    Optional<UnitOfMeashure> findByDescription(String description);
}
