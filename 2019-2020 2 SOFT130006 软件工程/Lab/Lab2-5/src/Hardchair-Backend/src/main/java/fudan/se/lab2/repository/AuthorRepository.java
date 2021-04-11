package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author LBW
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByAuthorName(String authorName);
    Set<Author> findByPaperId(long paperID);
    Author findByAntuorId(Long authorId);
}
