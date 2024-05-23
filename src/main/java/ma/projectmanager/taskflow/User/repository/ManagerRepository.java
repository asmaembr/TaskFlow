package ma.projectmanager.taskflow.User.repository;


import ma.projectmanager.taskflow.User.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
