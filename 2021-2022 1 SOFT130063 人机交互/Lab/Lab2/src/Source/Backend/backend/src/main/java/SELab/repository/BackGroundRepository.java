package SELab.repository;

import SELab.domain.BackGround;
import SELab.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackGroundRepository extends CrudRepository<BackGround, Long> {
    BackGround findById(long id);
    List<BackGround> findByGroundName(String groundName);
}
