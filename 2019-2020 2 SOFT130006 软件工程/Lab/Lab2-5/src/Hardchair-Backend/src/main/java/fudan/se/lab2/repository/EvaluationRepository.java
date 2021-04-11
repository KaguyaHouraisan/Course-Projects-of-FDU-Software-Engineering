package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Evaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author LBW
 */
@Repository
public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {
    Evaluation findByEvaluationId(Long evaluationId);
}
