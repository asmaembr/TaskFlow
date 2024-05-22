package ma.projectmanager.taskflow.Notification.controllers;

import ma.projectmanager.taskflow.Notification.model.Notification;
import ma.projectmanager.taskflow.Notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepository repository ;

    @RequestMapping("")
    public String notificationList(Model model){
        List<Notification> notifications = repository.findAll();
        model.addAttribute("notifications", notifications);
        return "notifications/index";
    }
}
