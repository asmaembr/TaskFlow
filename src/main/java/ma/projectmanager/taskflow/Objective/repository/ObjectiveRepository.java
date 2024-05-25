package ma.projectmanager.taskflow.Objective.repository;

import ma.projectmanager.taskflow.Objective.model.Objective;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjectiveRepository extends JpaRepository<Objective, Integer> {

}
