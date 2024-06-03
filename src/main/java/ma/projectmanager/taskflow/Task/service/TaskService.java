package ma.projectmanager.taskflow.Task.service;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Notification.model.Notification;
import ma.projectmanager.taskflow.Notification.model.NotificationType;
import ma.projectmanager.taskflow.Notification.repository.NotificationRepository;
import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Objective.repository.ObjectiveRepository;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Task.model.Task;
import ma.projectmanager.taskflow.Project.repository.ProjectRepository;
import ma.projectmanager.taskflow.Task.model.TaskStatus;
import ma.projectmanager.taskflow.Task.repository.TaskRepository;
import ma.projectmanager.taskflow.User.model.Manager;
import ma.projectmanager.taskflow.User.repository.ManagerRepository;
import ma.projectmanager.taskflow.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public Page<Task> getAllTasks(HttpSession session, int pageNbr, int nbrElementsParPage, String motCle){

        Page<Task> tasks =null ;
        if (session.getAttribute("role").equals("MAN")) {
            Manager manager = managerRepository.findByUsernameAndPassword(
                    (String) session.getAttribute("username"),
                    (String) session.getAttribute("password"));
            if (manager != null) {
                tasks = taskRepository.findAllByObjectiveProjectManagerIdAndDescriptionContainsIgnoreCase(
                        manager.getId(),
                        PageRequest.of(pageNbr, nbrElementsParPage),
                        motCle);
                }
            }
        else
        {/*
            tasks = memberRepository.findByUsernameAndPassword(
                    (String) session.getAttribute("username"),
                    (String) session.getAttribute("password")
            ).getTasks();*/
            tasks = taskRepository.findAllByMemberIdAndDescriptionContainingIgnoreCaseOrderByPriority(
                    memberRepository.findByUsernameAndPassword(
                            (String) session.getAttribute("username"),
                            (String) session.getAttribute("password")).getId(),
                    PageRequest.of(pageNbr, nbrElementsParPage),
                    motCle);
        }
        return tasks;
    }


    public void delete(int id){
        taskRepository.deleteById(id);
    }

    public Task getTask( int id ){
        Task task = taskRepository.findById(id);
        return task;
    }



    public void saveTask(Task task){
        if(taskRepository.findById(task.getId()) != null){
            Task task1 = taskRepository.findById(task.getId());
            task1.setDescription(task.getDescription());
            task1.setEndDate(task.getEndDate());
            task1.setStartDate(task.getStartDate());
            task1.setObjective(objectiveRepository.findById(task.getObjective().getId()));
            task1.setMember(memberRepository.findById(task.getMember().getId()));
            task1.setPriority(task.getPriority());
            task1.setStatus(task.getStatus());
            taskRepository.save(task1);
            notificationRepository.save(new Notification(
                    task1,
                    task1.getDescription()+" updated",
                    NotificationType.MESSAGE_APP,
                    task1.getStartDate()
            ));

        }
        else {
            task.setObjective(objectiveRepository.findById(task.getObjective().getId()));
            task.setMember(memberRepository.findById(task.getMember().getId()));
            task.setStatus(task.getStatus());
            taskRepository.save(task);
            notificationRepository.save(new Notification(
                    task,
                    task.getDescription(),
                    NotificationType.MESSAGE_APP,
                    task.getStartDate()
            ));
        }

    }

    public void markAsDone(int id) {
        Task task = getTask(id);
        task.setStatus(TaskStatus.DONE);
        saveTask(task);
    }
}
