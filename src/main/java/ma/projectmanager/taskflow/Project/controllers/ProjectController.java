package ma.projectmanager.taskflow.Project.controllers;

import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private  ProjectRepository projectRepository;

@GetMapping({"","/"})
    public String projectList(Model model ) {

    List<Project> projects = projectRepository.findAll();
    model.addAttribute("projects", projects);
    return "projects/index";
}

@GetMapping("/add")
    public String AddForm(Model model) {
    Project project = new Project();
    model.addAttribute("project", project);
    return "projects/add";
}
}
