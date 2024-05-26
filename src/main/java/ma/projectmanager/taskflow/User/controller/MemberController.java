package ma.projectmanager.taskflow.User.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/member")
public class MemberController{
    @RequestMapping("/")
    public String index(HttpSession session){
        return "members";
    }
    @RequestMapping("/add")
    public String add(HttpSession session){
        return "forms/member";
    }
    @RequestMapping("/delete")
    public String delete(HttpSession session){
        return "members";
    }
    @RequestMapping("/edit")
    public String edit(HttpSession session){
        return "forms/member";
    }
    @RequestMapping("/save")
    public String save(HttpSession session){
        return "redirect:members";
    }
}