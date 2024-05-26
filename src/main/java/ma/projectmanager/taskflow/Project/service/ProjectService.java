package ma.projectmanager.taskflow.Project.service;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Project.repository.ProjectRepository;
import ma.projectmanager.taskflow.User.repository.ManagerRepository;
import ma.projectmanager.taskflow.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Project> getAllProjects(HttpSession session){

        List<Project> projects = null;
        if(session.getAttribute("role").equals("MAN")){
            projects= projectRepository.findAllByManagerId(
                    managerRepository.findByUsernameAndPassword(
                            (String) session.getAttribute("username"),
                            (String) session.getAttribute("password")).getId());
        }
        else if(session.getAttribute("role").equals("MEM")){
            projects = projectRepository.findAll()
                    .stream()
                    .filter(project -> project.getObjectiveList()
                            .stream()
                            .flatMap(objective -> objective.getTasks().stream()) // Flatten the tasks stream
                            .anyMatch(task -> task.getMember().equals(memberRepository.findByUsernameAndPassword(
                                    (String) session.getAttribute("username"),
                                    (String) session.getAttribute("password"))
                            ))
                    )
                    .collect(Collectors.toList());

        }
        return projects;
    }

    public void delete(int id){
        projectRepository.deleteById(id);
    }

    public Project getProject( int id ){
        Project project = projectRepository.findById(id);
        return project;
    }


    public void saveProject(Project project , HttpSession session){
        if(projectRepository.findById(project.getId()) != null){
            Project project1 = projectRepository.findById(project.getId());
            project1.setDescription(project.getDescription());
            project1.setEndDate(project.getEndDate());
            project1.setStartDate(project.getStartDate());
            projectRepository.save(project1);
        }
        else {
            project.setManager(managerRepository.findByUsernameAndPassword(
                    (String) session.getAttribute("username"),
                    (String) session.getAttribute("password")));
            managerRepository.findByUsernameAndPassword(
                            (String) session.getAttribute("username"),
                            (String) session.getAttribute("password"))
                    .getProjects().add(project);
            projectRepository.save(project);
        }

    }
}
