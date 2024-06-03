package ma.projectmanager.taskflow.Project.repository;

import ma.projectmanager.taskflow.Project.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByManagerId(int managerId);
    Page<Project> findProjectsByManagerIdAndDescriptionContainingIgnoreCase(int managerId, String motCle, Pageable pageable);
    Page<Project> findAllByObjectiveListTasksMemberIdAndDescriptionContainsIgnoreCase(int memberId, PageRequest of , String motCle);
    Project findById(int id);
}
