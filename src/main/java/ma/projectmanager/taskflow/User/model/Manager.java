package ma.projectmanager.taskflow.User.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Project.model.Project;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MAN")
public class Manager extends User {


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "manager")
    private List<Project> projects =new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager manager)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(projects, manager.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), projects);
    }

    public Manager(int id, String username, String password, String fullName, String tel, LocalDate birthDay, String email, String role, LocalDateTime inscriptionDate, Sex sex, Skill skill) {
        super(id, username, password, fullName, tel, birthDay, email, role, inscriptionDate, sex, skill);
    }
}
