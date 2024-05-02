package techsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsphere.entity.User;

public interface userRepostory extends JpaRepository<User,Integer> {

}
