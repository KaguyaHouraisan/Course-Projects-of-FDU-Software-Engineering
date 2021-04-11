package DataBase1.repository;

import DataBase1.relation.Patient_WardNurse_Relation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Patient_WardNurse_Relation_Repository extends CrudRepository<Patient_WardNurse_Relation, Long> {

    List<Patient_WardNurse_Relation> findByWardNurseId(Long workId);

    Patient_WardNurse_Relation findByPatientId(long patientId);
}
