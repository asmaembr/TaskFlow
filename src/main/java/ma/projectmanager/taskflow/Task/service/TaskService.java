package ma.projectmanager.taskflow.Task.service;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Objective.repository.ObjectiveRepository;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Task.model.Task;
import ma.projectmanager.taskflow.Project.repository.ProjectRepository;
import ma.projectmanager.taskflow.Task.repository.TaskRepository;
import ma.projectmanager.taskflow.User.model.Manager;
import ma.projectmanager.taskflow.User.model.User;
import ma.projectmanager.taskflow.User.repository.ManagerRepository;
import ma.projectmanager.taskflow.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ObjectiveRepository objectiveRepository;


    public List<Task> getAllTasks(HttpSession session){

        List<Task> tasks =new ArrayList<>();

        if (session.getAttribute("role").equals("MAN")) {
            Manager manager = managerRepository.findByUsernameAndPassword(
                    (String) session.getAttribute("username"),
                    (String) session.getAttribute("password"));
            if (manager != null) {
                List<Project> projects = manager.getProjects();
                for (Project project : projects) {
                    List<Objective> objectives = project.getObjectiveList();
                    for (Objective objective : objectives) {
                        List<Task>tasks1 = objective.getTasks();
                        tasks.addAll(tasks1);
                    }
                }
            }
        }
        else
        {
            tasks = memberRepository.findByUsernameAndPassword(
                    (String) session.getAttribute("username"),
                    (String) session.getAttribute("password")
            ).getTasks();
        }
        return tasks;
    }


    public void delete(int id){
        taskRepository.deleteById(id);
    }

    public Task getTask( int id ){
        Task Task = taskRepository.findById(id);
        return Task;
    }



    public void saveTask(Task task){
        if(taskRepository.findById(task.getId()) != null){
            Task Task1 = taskRepository.findById(task.getId());
            Task1.setDescription(task.getDescription());
            Task1.setEndDate(task.getEndDate());
            Task1.setStartDate(task.getStartDate());
            Task1.setObjective(objectiveRepository.findById(task.getObjective().getId()));
            Task1.setMember(memberRepository.findById(task.getMember().getId()));
            Task1.setPriority(task.getPriority());
            Task1.setStatus(task.getStatus());
            taskRepository.save(Task1);
        }
        else {
            task.setObjective(objectiveRepository.findById(task.getObjective().getId()));
            task.setMember(memberRepository.findById(task.getMember().getId()));
            task.setStatus(task.getStatus());
            taskRepository.save(task);
        }

    }
}
