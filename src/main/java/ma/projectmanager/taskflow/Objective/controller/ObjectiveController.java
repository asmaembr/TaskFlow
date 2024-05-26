package ma.projectmanager.taskflow.Objective.controller;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Objective.service.ObjectiveService;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/objective")
public class ObjectiveController {

    @Autowired
    private ObjectiveService objectiveService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("")
    public String index( HttpSession session , Model model){
        List<Objective> objectives = objectiveService.getAllObjectives(session);
        List<Project> projects = projectService.getAllProjects(session);
        model.addAttribute("objectives",objectives);
        model.addAttribute("projects",projects);
        model.addAttribute("menu",session.getAttribute("menu"));
        return "objectives";
    }

    @GetMapping("/add")
    public String add(Model model , HttpSession session){
        List<Project> projects = projectService.getAllProjects(session);
        model.addAttribute("projects",projects);
        model.addAttribute("objective", new Objective());
        return "forms/objective";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam int id){
        objectiveService.delete(id);
        return "redirect:/objective";
    }
    @PostMapping("/edit")
    public String edit(@RequestParam int id ,Model model, HttpSession session){
        Objective objective = objectiveService.getObjective(id);
        List<Project> projects = projectService.getAllProjects(session);
        model.addAttribute("projects",projects);
        model.addAttribute("objective",objective);
        return "forms/objective";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Objective objective , HttpSession session, Model model){
        objectiveService.saveObjective(objective);
        model.addAttribute("menu",session.getAttribute("menu"));
        return "redirect:/objective";
    }

}
