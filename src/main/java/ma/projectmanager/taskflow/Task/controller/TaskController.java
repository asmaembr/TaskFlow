package ma.projectmanager.taskflow.Task.controller;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Objective.service.ObjectiveService;
import ma.projectmanager.taskflow.Task.model.Task;
import ma.projectmanager.taskflow.Task.model.TaskStatus;
import ma.projectmanager.taskflow.Task.service.TaskService;
import ma.projectmanager.taskflow.User.model.Member;
import ma.projectmanager.taskflow.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController {

   @Autowired
   private TaskService  taskService;

    @Autowired
    private ObjectiveService objectiveService;

    @Autowired
    private MemberRepository memberRepository;


    @RequestMapping("")
    public String index(HttpSession session , Model model) {
        List<Task> tasks = taskService.getAllTasks(session);
        model.addAttribute("tasks", tasks);
        model.addAttribute("menu",session.getAttribute("menu"));
        return "tasks";
    }

    @GetMapping("/add")
    public String add(Model model , HttpSession session){

        List<Objective> objectives = objectiveService.getAllObjectives(session);
        model.addAttribute("objectives",objectives);
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        model.addAttribute("task", new Task());
        model.addAttribute("menu",session.getAttribute("menu"));
        return "forms/task";
    }

    @PostMapping ("/delete")
    public String delete(@RequestParam int id) {
        memberRepository.deleteById(id);
        return "redirect:/task";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam int id, Model model , HttpSession session) {
        Task task = taskService.getTask(id);
        List<Objective> objectives = objectiveService.getAllObjectives(session);
        model.addAttribute("objectives",objectives);
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        model.addAttribute("task",task);
        model.addAttribute("menu",session.getAttribute("menu"));
        return "forms/task";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/task";
    }

    @PostMapping("/updateTaskStatus")
    @ResponseBody
    public ResponseEntity<?> updateTaskStatus(@RequestBody Map<String, String> payload) {
        String taskId = payload.get("taskId");
        String newStatus = payload.get("newStatus");

        // Find the task by ID and update its status
        Task task = taskService.getTask(Integer.parseInt(taskId));
        task.setStatus(TaskStatus.valueOf(newStatus));
        taskService.saveTask(task);

        return ResponseEntity.ok().body("Task status updated successfully");
    }

}
