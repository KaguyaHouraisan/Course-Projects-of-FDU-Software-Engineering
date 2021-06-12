package SQAT.repository;

import SQAT.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Set<Product> findByProductType(int productType);
    Product findByProductNum(String productNum);
}
