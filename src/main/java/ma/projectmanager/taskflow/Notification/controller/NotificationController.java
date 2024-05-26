package ma.projectmanager.taskflow.Notification.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    @RequestMapping("/")
    public String index( HttpSession session){
        return "notifications";
    }

    @RequestMapping("/add")
    public String add(HttpSession session){
        return "forms/notification";
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session){
        return "notifications";
    }

    @RequestMapping("/edit")
    public String edit(HttpSession session){
        return "forms/notification";
    }

    @RequestMapping("/save")
    public String save(HttpSession session){
        return "redirect:notifications";
    }


}
