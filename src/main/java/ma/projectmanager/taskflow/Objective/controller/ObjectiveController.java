package ma.projectmanager.taskflow.Objective.controller;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Objective.service.ObjectiveService;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String index( HttpSession session , Model model,
        @RequestParam(name = "pageNbr", defaultValue = "0") int pageNbr,
        @RequestParam(name = "nbrElt", defaultValue = "2") int nbrElementsParPage,
        @RequestParam(name = "motCle",defaultValue = "") String motCle){
        Page<Project> projects = projectService.getAllProjectsPages(session, pageNbr, nbrElementsParPage, motCle);
            int totalPages = projects.getTotalPages();
            int[] pages = new int[totalPages];
            for (int i = 0; i < totalPages; i++) pages[i] = i;
            model.addAttribute("pages", pages);
            model.addAttribute("pageCourante",  pageNbr);
            model.addAttribute("motCle",motCle);
            model.addAttribute("projects",projects.getContent());
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
    public String save(@ModelAttribute Objective objective){
        objectiveService.saveObjective(objective);
        return "redirect:/objective";
    }

}
