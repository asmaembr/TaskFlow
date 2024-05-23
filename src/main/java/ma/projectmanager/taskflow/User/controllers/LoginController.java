package ma.projectmanager.taskflow.User.controllers;

import jakarta.servlet.http.HttpSession;
import ma.projectmanager.taskflow.User.model.User;
import ma.projectmanager.taskflow.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/login", ""})
public class LoginController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private HttpSession httpSession;

    @GetMapping({"", "/logout"})
    public String pageLogin() {
        return "index";
    }


    @PostMapping("")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        if (username == null || password == null) {
            return "index";
        }
        else if(repository.findByUsernameAndPassword(username , password)!=null) {
            User user = repository.findByUsernameAndPassword(username, password);
            httpSession.setAttribute("user", user);
            User iuser= (User) httpSession.getAttribute("user");
            System.out.println(iuser);
            return "projects/index";
        }
        else {
            return "index";
        }
    }

}
