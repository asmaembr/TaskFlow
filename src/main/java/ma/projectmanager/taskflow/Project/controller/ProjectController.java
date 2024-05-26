package ma.projectmanager.taskflow.Project.controller;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Project.repository.ProjectRepository;
import ma.projectmanager.taskflow.User.repository.ManagerRepository;
import ma.projectmanager.taskflow.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private MemberRepository memberRepository;


    @RequestMapping("")
    public String index(HttpSession session, Model model){

        List<Project> projects = null;
        if(session.getAttribute("role").equals("MAN")){
            projects= projectRepository.findAllByManagerId(
                    managerRepository.findByUsernameAndPassword(
                            (String) session.getAttribute("username"),
                            (String) session.getAttribute("password")).getId());
        }
        else if(session.getAttribute("role").equals("MEM")){
            projects = projectRepository.findAll()
                    .stream()
                    .filter(project -> project.getObjectiveList()
                            .stream()
                            .flatMap(objective -> objective.getTasks().stream()) // Flatten the tasks stream
                            .anyMatch(task -> task.getMember().equals(memberRepository.findByUsernameAndPassword(
                                    (String) session.getAttribute("username"),
                                    (String) session.getAttribute("password"))
                            ))
                    )
                    .collect(Collectors.toList());

        }
        model.addAttribute("menu",session.getAttribute("menu"));
        model.addAttribute("projects",projects);
        return "projects";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("project", new Project());
        return "forms/project";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id){
        projectRepository.deleteById(id);
        return "redirect:/project";
    }
    @PostMapping("/edit")
    public String edit(@RequestParam int id ,Model model){
        Project project = projectRepository.findById(id);
        model.addAttribute("project",project);
        return "forms/project";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Project project ,HttpSession session, Model model){
        if(projectRepository.findById(project.getId()) != null){
            System.out.println("edit");
            Project project1 = projectRepository.findById(project.getId());
            project1.setDescription(project.getDescription());
            project1.setEndDate(project.getEndDate());
            project1.setStartDate(project.getStartDate());
            projectRepository.save(project1);
        }
        else {
            System.out.println("add");
            project.setManager(managerRepository.findByUsernameAndPassword(
                    (String) session.getAttribute("username"),
                    (String) session.getAttribute("password")));
            managerRepository.findByUsernameAndPassword(
                            (String) session.getAttribute("username"),
                            (String) session.getAttribute("password"))
                    .getProjects().add(project);
            projectRepository.save(project);
        }
        model.addAttribute("menu",session.getAttribute("menu"));
        return "redirect:/project";
    }

}
