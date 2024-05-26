package ma.projectmanager.taskflow.User.repository;


import ma.projectmanager.taskflow.User.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {


    Manager findByUsernameAndPassword(String username, String password);
}
