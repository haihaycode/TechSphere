package techsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsphere.entity.Post;
import techsphere.entity.User;

public interface PostRepository  extends JpaRepository<Post,Integer> {
}
