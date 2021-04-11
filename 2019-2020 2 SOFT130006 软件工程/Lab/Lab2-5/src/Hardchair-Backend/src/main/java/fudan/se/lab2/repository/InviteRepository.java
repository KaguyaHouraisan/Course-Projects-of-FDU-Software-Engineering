package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Invite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

/**
 * @author LBW
 */
@Repository
public interface InviteRepository extends CrudRepository<Invite, Long> {
    Set<Invite> findByPcmemberName(String pcmemberName);
    Set<Invite> findByChairName(String chairName);
    Set<Invite> findByConferenceName(String conferenceName);
}
