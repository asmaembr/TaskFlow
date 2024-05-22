package ma.projectmanager.taskflow.Task.controllers;
import ma.projectmanager.taskflow.Task.model.Task;
import ma.projectmanager.taskflow.Task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository repository ;

    @GetMapping({"","/"})
    public String taskList(Model model ){

        List<Task> tasks= repository.findAll();
        model.addAttribute("tasks",tasks);
        return "tasks/index";
    }



}
