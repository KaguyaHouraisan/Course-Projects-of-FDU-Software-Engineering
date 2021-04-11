package DataBase1.repository;

import DataBase1.domain.RegulatoryTasks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulatoryTasksRepository extends CrudRepository<RegulatoryTasks, Long> {

    RegulatoryTasks findById(long Id);
    RegulatoryTasks findByName(String name);
}
