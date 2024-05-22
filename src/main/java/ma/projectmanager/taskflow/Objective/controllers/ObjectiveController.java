package ma.projectmanager.taskflow.Objective.controllers;

import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.Objective.repository.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/objectives")
public class ObjectiveController {
    @Autowired
    private ObjectiveRepository repository ;

    @RequestMapping("")
    public String objectiveList(Model model ){
        List<Objective> objectives = repository.findAll();
        model.addAttribute("objectives", objectives);
        return "objectives/index";
    }
}
