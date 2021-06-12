package SQAT.repository;

import SQAT.domain.LoanAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface LoanAccountRepository extends CrudRepository<LoanAccount, Long> {
    Set<LoanAccount> findByCustomerCode(String customerCode);
    Set<LoanAccount> findByAccountNum(String accountNum);
    LoanAccount findByIouNum(String iouNum);
}
