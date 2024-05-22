package ma.projectmanager.taskflow.Objective.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Task.model.Task;


import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private LocalDate startDate ;
    private LocalDate endDate ;
    private String description ;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;
}
