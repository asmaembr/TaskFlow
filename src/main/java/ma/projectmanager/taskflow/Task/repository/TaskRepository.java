package ma.projectmanager.taskflow.Task.repository;

import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer>{
    Task findById(int id);
}
