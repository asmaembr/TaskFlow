package ma.projectmanager.taskflow.User.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Task.model.Task;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("MEM")
public class Member extends User {

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

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

    public Member(int id, String username, String password, String fullName, String tel, LocalDate birthDay, String email, String role, LocalDateTime inscriptionDate, Sex sex, Skill skill) {
        super(id, username, password, fullName, tel, birthDay, email, role, inscriptionDate, sex, skill);
    }
}
