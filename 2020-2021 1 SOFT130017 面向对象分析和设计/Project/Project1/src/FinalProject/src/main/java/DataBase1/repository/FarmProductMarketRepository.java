package DataBase1.repository;

import DataBase1.domain.FarmProductMarket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmProductMarketRepository extends CrudRepository<FarmProductMarket, Long> {

    FarmProductMarket findById(long id);

}
