package SQAT.repository;

import SQAT.domain.LoanPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface LoanPlanRepository extends CrudRepository<LoanPlan, Long> {
    Set<LoanPlan> findByIouNum(String iouNum);
    Set<LoanPlan> findByIouNumAndRepaymentStatus(String iouNum, int repaymentStatus);
    Set<LoanPlan> findByPlanDateAndAndRepaymentStatus(String date, int repaymentStatus);
}
