package ma.projectmanager.taskflow.Objective.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/objective")
public class ObjectiveController {
    @RequestMapping("/")
    public String index( HttpSession session){
        return "objectives";
    }

    @RequestMapping("/add")
    public String add(HttpSession session){
        return "forms/objective";
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session){
        return "objectives";
    }

    @RequestMapping("/edit")
    public String edit(HttpSession session){
        return "forms/objective";
    }

    @RequestMapping("/save")
    public String save(HttpSession session){
        return "redirect:objectives";
    }
}
