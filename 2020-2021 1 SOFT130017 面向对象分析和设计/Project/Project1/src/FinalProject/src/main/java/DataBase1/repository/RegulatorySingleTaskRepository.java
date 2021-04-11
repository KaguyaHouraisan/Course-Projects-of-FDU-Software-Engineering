package DataBase1.repository;

import DataBase1.relation.RegulatorySingleTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegulatorySingleTaskRepository extends CrudRepository<RegulatorySingleTask, Long> {
        RegulatorySingleTask findById(long Id);
        RegulatorySingleTask findByFarmProductMarketIdAndRegulatoryTaskIdAndIfSampling(long farmProductMarketId, long regularTaskId, boolean ifSampling);
        List<RegulatorySingleTask> findByRegulatoryTaskIdAndIfSampling(long regularTaskId, boolean ifSampling);
        List<RegulatorySingleTask> findByFarmProductMarketIdAndIfSampling(long farmProductMarketId, boolean ifSampling);
}
