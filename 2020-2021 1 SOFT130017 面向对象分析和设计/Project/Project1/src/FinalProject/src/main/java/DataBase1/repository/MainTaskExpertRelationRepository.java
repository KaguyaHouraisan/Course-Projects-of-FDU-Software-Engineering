package DataBase1.repository;

import DataBase1.domain.Expert;
import DataBase1.relation.MainTaskExpertRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MainTaskExpertRelationRepository extends CrudRepository<MainTaskExpertRelation, Long> {

    List<MainTaskExpertRelation> findByExpertId(long Id);

    MainTaskExpertRelation findByMainTaskId(long id);
    MainTaskExpertRelation findByExpertIdAndMainTaskId(long expertId, long mainTaskId);
}