package DataBase1.repository;

import DataBase1.domain.DailyRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DailyRegistrationRepository extends CrudRepository<DailyRegistration, Long> {
    List<DailyRegistration> findByPatientId(Long patientId);
}
