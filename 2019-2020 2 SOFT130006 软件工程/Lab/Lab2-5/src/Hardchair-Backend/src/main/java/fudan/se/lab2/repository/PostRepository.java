package fudan.se.lab2.repository;


import fudan.se.lab2.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LBW
 */
@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Post findByPostId(Long postId);
}
