package DataBase1.repository;

import DataBase1.domain.Bed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BedRepository extends CrudRepository<Bed, Long> {
    List<Bed> findByTreatRegion(String treatRegion);
    Bed findById(long id);
}
