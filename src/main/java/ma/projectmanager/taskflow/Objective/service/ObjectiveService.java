package ma.projectmanager.taskflow.Objective.service;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Objective.repository.ObjectiveRepository;
import ma.projectmanager.taskflow.Project.repository.ProjectRepository;
import ma.projectmanager.taskflow.User.repository.ManagerRepository;
import ma.projectmanager.taskflow.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ObjectiveService {
    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProjectRepository projectRepository;


    public List<Objective> getAllObjectives(HttpSession session){

        List<Objective> objectives = null;
        if(session.getAttribute("role").equals("MAN")) {
            objectives = objectiveRepository.findAll()
                    .stream()
                    .filter(objective -> objective.getProject().getManager().equals(
                            managerRepository.findByUsernameAndPassword(
                            (String) session.getAttribute("username"),
                            (String) session.getAttribute("password"))
                    ))
                    .toList();
        }
        else if(session.getAttribute("role").equals("MEM")){
            objectives = objectiveRepository.findAll()
                    .stream()
                    .filter(objective -> objective.getTasks()
                            .stream()
                            .anyMatch(task -> task.getMember().equals(memberRepository.findByUsernameAndPassword(
                                    (String) session.getAttribute("username"),
                                    (String) session.getAttribute("password"))
                            ))
                    ).toList();
        }
        return objectives;
    }


    public void delete(int id){
        objectiveRepository.deleteById(id);
    }

    public Objective getObjective( int id ){
        Objective objective = objectiveRepository.findById(id);
        return objective;
    }



    public void saveObjective(Objective objective){
        if(objectiveRepository.findById(objective.getId()) != null){
            Objective objective1 = objectiveRepository.findById(objective.getId());
            objective1.setDescription(objective.getDescription());
            objective1.setEndDate(objective.getEndDate());
            objective1.setStartDate(objective.getStartDate());
            objective1.setProject(projectRepository.findById(objective.getProject().getId()));
            objectiveRepository.save(objective1);
        }
        else {
            objective.setProject(projectRepository.findById(objective.getProject().getId()));
            objectiveRepository.save(objective);
        }

    }
}
