package ma.projectmanager.taskflow.Task.repository;

import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Task.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer>{
    Page<Task> findAllByMemberIdAndDescriptionContainingIgnoreCaseOrderByPriority(int memberId, PageRequest of , String motCle);
    Page<Task> findAllByObjectiveProjectManagerIdAndDescriptionContainsIgnoreCase(int managerId, PageRequest of, String motCle);
    Task findById(int id);
}
