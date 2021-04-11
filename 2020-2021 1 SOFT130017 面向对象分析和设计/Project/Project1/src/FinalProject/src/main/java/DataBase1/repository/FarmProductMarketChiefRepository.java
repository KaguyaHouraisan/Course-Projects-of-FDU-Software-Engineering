package DataBase1.repository;

import DataBase1.domain.FarmProductMarketChief;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmProductMarketChiefRepository extends CrudRepository<FarmProductMarketChief, Long> {
    FarmProductMarketChief findByFarmProductMarketId(long id);
}
