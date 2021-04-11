package fudan.se.lab2.repository;


import fudan.se.lab2.domain.Paper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author LBW
 */
@Repository
public interface PaperRepository extends CrudRepository<Paper, Long> {
    Paper findByPaperId(Long paperId);
    Paper findByHeadline(String headline);
}
