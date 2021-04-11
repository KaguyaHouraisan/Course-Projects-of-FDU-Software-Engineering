package DataBase1.repository;

import DataBase1.domain.NucleicAcidDetectionReceipt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NucleicAcidDetectionRepository extends CrudRepository<NucleicAcidDetectionReceipt, Long> {
    List<NucleicAcidDetectionReceipt> findNucleicAcidDetectionReceiptsByPatientId(long patientId);
}
