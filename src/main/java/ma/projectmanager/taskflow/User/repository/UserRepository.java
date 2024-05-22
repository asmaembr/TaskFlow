package ma.projectmanager.taskflow.User.repository;

import ma.projectmanager.taskflow.User.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

}
