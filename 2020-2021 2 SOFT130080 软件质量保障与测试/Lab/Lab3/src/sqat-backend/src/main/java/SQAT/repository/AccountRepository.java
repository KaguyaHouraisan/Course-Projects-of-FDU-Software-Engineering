package SQAT.repository;

import SQAT.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByAccountNum(String accountNum);
}
