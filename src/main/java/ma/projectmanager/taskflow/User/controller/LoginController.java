package ma.projectmanager.taskflow.User.controller;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.User.model.User;
import ma.projectmanager.taskflow.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/login", ""})
public class LoginController {
    @Autowired
    private UserRepository repository;


    @RequestMapping({"/", "/logout"})
    public String login() {
        return "login";
    }


    @PostMapping("")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {

        User user = repository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("username",user.getUsername());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("id",user.getId());
            session.setAttribute("role", user.getRole());
            if (user.getRole().equals("MEM")) {
                session.setAttribute("menu", "MemberMenu");
                return "redirect:/notification";
            } else {
                session.setAttribute("menu", "ManagerMenu");
                return "redirect:/project";
            }
        }
        else {
            model.addAttribute("error", "Username or Password not correct!");
            return "login";
        }
    }

}
