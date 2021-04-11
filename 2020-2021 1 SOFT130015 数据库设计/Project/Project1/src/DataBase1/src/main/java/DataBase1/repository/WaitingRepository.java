package DataBase1.repository;

import DataBase1.relation.Waiting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WaitingRepository extends CrudRepository<Waiting, Long> {

    Waiting findByPatientId(Long patientId);
    Waiting findById(long id);

    List<Waiting> findByTargetRegionAndWaitingType(String targetRegion, String waitingType);
}
