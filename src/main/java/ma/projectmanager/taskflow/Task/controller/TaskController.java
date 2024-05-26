package ma.projectmanager.taskflow.Task.controller;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Task.model.Task;
import ma.projectmanager.taskflow.Task.repository.TaskRepository;
import ma.projectmanager.taskflow.User.repository.ManagerRepository;
import ma.projectmanager.taskflow.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private TaskRepository taskRepository;


    @GetMapping("")
    public String index(HttpSession session , Model model) {
        List<Task> tasks;
        model.addAttribute("menu",session.getAttribute("menu"));
        if (session.getAttribute("role").equals("MAN")) {
            tasks = null;
            managerRepository.findByUsernameAndPassword(
                    (String) session.getAttribute("username"),
                    (String) session.getAttribute("password")
            ).getProjects()
                     .stream()
                     .forEach(p-> {
                                 p.getObjectiveList()
                                         .stream()
                                         .forEach(o -> {
                                             o.getTasks()
                                                     .stream()
                                                     .forEach(t -> tasks.add(t));
                                         });
                             }
                     );

        }
        else
        {
          tasks = memberRepository.findByUsernameAndPassword(
                    (String) session.getAttribute("username"),
                    (String) session.getAttribute("password")
            ).getTasks();
        }

        model.addAttribute("tasks", tasks);


        return "tasks";
    }

    @GetMapping("/add")
    public String add(){
        return "forms/task";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id, HttpSession session , Model model) {
        taskRepository.deleteById(id);
        model.addAttribute("menu",session.getAttribute("menu"));
        return "tasks";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Task task = taskRepository.findById(id).get();
        model.addAttribute("task",task);
        return "forms/task";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Task task, HttpSession session , Model model) {
        taskRepository.save(task);
        model.addAttribute("menu",session.getAttribute("menu"));
        return "redirect:tasks";
    }

}
