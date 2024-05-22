package ma.projectmanager.taskflow.Project.repository;

import ma.projectmanager.taskflow.Project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
