package ma.projectmanager.taskflow.User.controllers;

import ma.projectmanager.taskflow.User.model.User;
import ma.projectmanager.taskflow.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserRepository repository;
    @GetMapping("")
    public String register() {
        return "register";
    }

    @PostMapping("")
    public String registerUser(@ModelAttribute User user) {
        if (user == null) {
            return "register";
        }
        System.out.println(user);
        repository.save(user);
        return "index";
    }
}
