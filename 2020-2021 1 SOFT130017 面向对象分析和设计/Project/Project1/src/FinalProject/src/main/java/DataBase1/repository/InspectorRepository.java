package DataBase1.repository;

import DataBase1.domain.Inspector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectorRepository extends CrudRepository<Inspector, Long> {

}
