package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Conference;
import fudan.se.lab2.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

/**
 * @author Renhao Liu
 */
@Repository
public interface ConferenceRepository extends CrudRepository<Conference, Long> {
    Conference findByfullName(String fullName);
    Conference findByConferenceId(Long conferenceID);
    Set<Conference> findByChairMan(User chairMan);
    Set<Conference> findByStage(String stage);
}

