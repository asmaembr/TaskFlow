package ma.projectmanager.taskflow.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

    @RequestMapping("/")
    public String index(HttpSession session) {
        return "tasks";
    }

    @GetMapping("/add")
    public String add(HttpSession session) {
        return "forms/task";
    }

    @GetMapping("/delete")
    public String delete(HttpSession session) {
        return "tasks";
    }

    @GetMapping("/edit")
    public String edit(HttpSession session) {
        return "forms/task";
    }

    @PostMapping("/save")
    public String save(HttpSession session) {
        return "redirect:tasks";
    }

}
