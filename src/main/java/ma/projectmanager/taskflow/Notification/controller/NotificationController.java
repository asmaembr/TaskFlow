package ma.projectmanager.taskflow.Notification.controller;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Notification.model.Notification;
import ma.projectmanager.taskflow.Notification.service.NotificationService;
import ma.projectmanager.taskflow.Task.model.Task;
import ma.projectmanager.taskflow.Task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private TaskService taskService;

    @RequestMapping("")
    public String index(HttpSession session , Model model){
        List<Notification> notifs = notificationService.getAllNotifications(session);
        model.addAttribute("notifs", notifs);
        return "notifications";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam int id){
        notificationService.delete(id);
        return "redirect:/notification";
    }


}
