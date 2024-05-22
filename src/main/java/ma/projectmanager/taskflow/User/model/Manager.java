package ma.projectmanager.taskflow.User.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Project.model.Project;


import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MAN")
public class Manager extends User {

    @Enumerated(EnumType.STRING)
    private Skill skill;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "manager")
    private List<Project> projects;

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
}
