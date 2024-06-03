package ma.projectmanager.taskflow.Task.controller;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Objective.service.ObjectiveService;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Task.model.Task;
import ma.projectmanager.taskflow.Task.service.TaskService;
import ma.projectmanager.taskflow.User.model.Member;
import ma.projectmanager.taskflow.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/task")
public class TaskController {

   @Autowired
   private TaskService  taskService;

    @Autowired
    private ObjectiveService objectiveService;

    @Autowired
    private MemberRepository memberRepository;

    private boolean done =false ;


    @RequestMapping("")
    public String index(HttpSession session , Model model,
                        @RequestParam(name = "pageNbr", defaultValue = "0") int pageNbr,
                        @RequestParam(name = "nbrElt", defaultValue = "5") int nbrElementsParPage,
                        @RequestParam(name = "motCle",defaultValue = "") String motCle){
        Page<Task> tasks = taskService.getAllTasks(session,pageNbr,nbrElementsParPage,motCle);
        int totalPages = tasks.getTotalPages();
        int[] pages = new int[totalPages];
        for (int i = 0; i < totalPages; i++) { pages[i] = i;}
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante",  pageNbr);
        model.addAttribute("motCle",motCle);
        model.addAttribute("tasks", tasks.getContent());
        if(done){
            model.addAttribute("message", "Task Marked as Done Successfully");
        }
       done= false ;
        return "tasks";
    }

    @GetMapping("/add")
    public String add(Model model , HttpSession session){

        List<Objective> objectives = objectiveService.getAllObjectives(session);
        model.addAttribute("objectives",objectives);
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        model.addAttribute("task", new Task());
        return "forms/task";
    }

    @PostMapping ("/delete")
    public String delete(@RequestParam int id) {
        taskService.delete(id);
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
        return "forms/task";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/task";
    }

    @PostMapping("/done")
    public String MarkAsDone(@RequestParam int id ) {
        taskService.markAsDone(id);
        done = true ;
        return "redirect:/task";
    }

}
