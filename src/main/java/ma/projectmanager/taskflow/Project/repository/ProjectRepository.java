package ma.projectmanager.taskflow.Project.repository;

import ma.projectmanager.taskflow.Project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByManagerId(int managerId);
    Project findById(int id);
}
