package DataBase1.repository;

import DataBase1.relation.Bed_Patient_Relation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Bed_Patient_Relation_Repository extends CrudRepository<Bed_Patient_Relation, Long> {
    List<Bed_Patient_Relation> findByTreatRegion(String treatRegion);
    Bed_Patient_Relation findByBedId(long bedId);
    Bed_Patient_Relation findByPatientId(long patientId);
}
