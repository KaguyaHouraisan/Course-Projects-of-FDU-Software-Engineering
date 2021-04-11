package DataBase1.repository;

import DataBase1.relation.ScoreChangeRecords;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreChangeRecordsRepository extends CrudRepository<ScoreChangeRecords, Long> {

        List<ScoreChangeRecords> findByTargetId(long id);
}
