package ma.projectmanager.taskflow.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ObjectiveController {
    @RequestMapping("/objective")
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
