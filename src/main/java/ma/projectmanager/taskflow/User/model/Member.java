package ma.projectmanager.taskflow.User.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Task.model.Task;


import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("MEM")
public class Member extends User {
    @Enumerated(EnumType.STRING)
    private Skill skill;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(tasks, member.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tasks);
    }
}
