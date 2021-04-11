package DataBase1.repository;

import DataBase1.domain.Expert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpertRepository extends CrudRepository<Expert, Long> {
    Expert findById(long id);
}
