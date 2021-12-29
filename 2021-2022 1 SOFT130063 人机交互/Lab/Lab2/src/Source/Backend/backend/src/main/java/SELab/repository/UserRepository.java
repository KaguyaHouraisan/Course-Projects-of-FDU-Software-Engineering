package SELab.repository;

import SELab.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findById(long id);
//    User findByEmail(String email);
//    Streamable<User> findByFullnameContains(String fullname);
//    User findByFullnameAndEmail(String fullname,String email);
}