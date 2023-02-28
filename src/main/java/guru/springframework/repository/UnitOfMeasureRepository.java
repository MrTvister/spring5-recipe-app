package guru.springframework.repository;

import guru.springframework.entity.UnitOfMeashure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeashure, Long> {
}
