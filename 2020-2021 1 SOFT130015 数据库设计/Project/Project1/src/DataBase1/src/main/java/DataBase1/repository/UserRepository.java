package DataBase1.repository;

import DataBase1.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByWorkId(long workID);
    List<User> findByRoleIdAndWorkingTreatRegion(Long roleId, String workingTreatRegion);
}