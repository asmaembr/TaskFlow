package ma.projectmanager.taskflow.Controllers;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Project.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {
     @RequestMapping("/")
    public String index( HttpSession session){
        return "projects";
    }
    @GetMapping("/add")
    public String add(HttpSession session){
        return "forms/project";
    }
    @GetMapping("/delete")
    public String delete( HttpSession session){
        return "project";
    }
    @GetMapping("/edit")
    public String edit( HttpSession session){
        return "forms/project";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Project project , HttpSession session){
        System.out.println(project);
        return "redirect:/manager/projects";
    }

}
