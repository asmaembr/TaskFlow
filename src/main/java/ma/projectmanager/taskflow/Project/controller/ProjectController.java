package ma.projectmanager.taskflow.Project.controller;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @RequestMapping("")
    public String index(HttpSession session, Model model){
        List<Project> projects = projectService.getAllProjects(session);
        model.addAttribute("projects",projects);
        model.addAttribute("menu",session.getAttribute("menu"));
        return "projects";
    }
    @GetMapping("/add")
    public String add(Model model,HttpSession session){

        model.addAttribute("project", new Project());
        model.addAttribute("menu",session.getAttribute("menu"));
        return "forms/project";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id){
      projectService.delete(id);
        return "redirect:/project";
    }
    @PostMapping("/edit")
    public String edit(@RequestParam int id ,Model model, HttpSession session){
        Project project = projectService.getProject(id);
        model.addAttribute("project",project);
        model.addAttribute("menu",session.getAttribute("menu"));
        return "forms/project";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Project project ,HttpSession session){
        projectService.saveProject(project,session);
        return "redirect:/project";
    }



}
