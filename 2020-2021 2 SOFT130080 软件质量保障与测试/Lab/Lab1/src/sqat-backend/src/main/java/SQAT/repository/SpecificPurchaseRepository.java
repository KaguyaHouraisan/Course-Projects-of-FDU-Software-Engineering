package SQAT.repository;

import SQAT.domain.SpecificPurchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface SpecificPurchaseRepository extends CrudRepository<SpecificPurchase, Long> {
    Set<SpecificPurchase> findByAccountNum(String accountNum);
}
