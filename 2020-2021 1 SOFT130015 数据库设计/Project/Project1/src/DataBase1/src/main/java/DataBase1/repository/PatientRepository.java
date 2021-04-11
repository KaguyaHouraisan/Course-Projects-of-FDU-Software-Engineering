package DataBase1.repository;

import DataBase1.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    Patient findByName(String name);
    Patient findPatientById(long patientId);
}
