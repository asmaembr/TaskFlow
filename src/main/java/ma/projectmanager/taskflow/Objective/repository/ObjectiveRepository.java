package ma.projectmanager.taskflow.Objective.repository;

import ma.projectmanager.taskflow.Objective.model.Objective;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectiveRepository extends JpaRepository<Objective, Integer> {
    void deleteById(int id);
    Objective findById(int id);
}
