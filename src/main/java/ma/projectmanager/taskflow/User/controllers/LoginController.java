package ma.projectmanager.taskflow.User.controllers;

import ma.projectmanager.taskflow.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("/login")
public class LoginController {
    @Autowired
    private UserRepository repository;

    public String login() {
        return "login";
    }

}
