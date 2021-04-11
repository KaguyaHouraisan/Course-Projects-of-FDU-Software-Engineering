package DataBase1.repository;

import DataBase1.relation.SamplingInspectionExpertRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SamplingInspectionExpertRelationRepository extends CrudRepository<SamplingInspectionExpertRelation, Long> {
    SamplingInspectionExpertRelation findByRegulatorySingleTaskId(long regulatorySingleTaskId);
}
