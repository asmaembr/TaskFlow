package ma.projectmanager.taskflow.Controllers;

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


    @GetMapping({"", "/logout"})
    public String login() {
        return "index";
    }


    @PostMapping("")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {

        User user = repository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            if (user.getRole().equals("MEM")) {
                session.setAttribute("dashboard","fragments :: MemberMenu");
                return "redirect:/tasks";
            } else {
                session.setAttribute("dashboard","fragments :: ManagerMenu");
                return "redirect:/projects";
            }
        }
        else {
            model.addAttribute("error", "Username ou Password invalide");
            return "index";
        }
    }

}
