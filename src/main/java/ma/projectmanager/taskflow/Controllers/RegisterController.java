package ma.projectmanager.taskflow.Controllers;

import ma.projectmanager.taskflow.User.model.Manager;
import ma.projectmanager.taskflow.User.model.Member;
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
        if (user.getRole().equals("MAN")) {
            Manager manager =     new Manager();
            manager.setId(user.getId());
            manager.setUsername(user.getUsername());
            manager.setPassword(user.getPassword());
            manager.setFullName(user.getFullName());
            manager.setTel(user.getTel());
            manager.setBirthDay(user.getBirthDay());
            manager.setEmail(user.getEmail());
            manager.setInscriptionDate(user.getInscriptionDate());
            manager.setSex(user.getSex());
            manager.setSkill(user.getSkill());
            repository.save(manager);
        }
        else if (user.getRole().equals("MEM") ){
            Member member = new Member();
            member.setId(user.getId());
            member.setUsername(user.getUsername());
            member.setPassword(user.getPassword());
            member.setFullName(user.getFullName());
            member.setTel(user.getTel());
            member.setBirthDay(user.getBirthDay());
            member.setEmail(user.getEmail());
            member.setInscriptionDate(user.getInscriptionDate());
            member.setSex(user.getSex());
            member.setSkill(user.getSkill());
            repository.save(member);
        }

        return "index";
    }
}
