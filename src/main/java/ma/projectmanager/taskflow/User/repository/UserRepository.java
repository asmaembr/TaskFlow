package ma.projectmanager.taskflow.User.repository;

import ma.projectmanager.taskflow.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {


    User findByUsernameAndPassword(String username, String password);
}
