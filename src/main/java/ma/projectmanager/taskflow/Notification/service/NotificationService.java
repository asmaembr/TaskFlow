package ma.projectmanager.taskflow.Notification.service;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Notification.model.Notification;
import ma.projectmanager.taskflow.Notification.repository.NotificationRepository;
import ma.projectmanager.taskflow.Task.model.Task;
import ma.projectmanager.taskflow.Task.repository.TaskRepository;
import ma.projectmanager.taskflow.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Notification> getAllNotifications(HttpSession session) {

        List<Notification> notifs =new ArrayList<>();
        if (session.getAttribute("role").equals("MAN")) {
          return null;
        }
        else
        {
            memberRepository.findByUsernameAndPassword(
                    (String) session.getAttribute("username"),
                    (String) session.getAttribute("password")
            ).getTasks().forEach(task -> {
                notifs.addAll(task.getNotifications());
            });
        }
        return notifs;
    }

    public void delete(int id){
        notificationRepository.deleteById(id);
    }

    public Notification getNotification(int id ){
        Notification notif = notificationRepository.findById(id);
        return notif;
    }


}
