package ma.projectmanager.taskflow.Objective.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Task.model.Task;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private LocalDate startDate ;
    private LocalDate endDate ;
    private String description ;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "objective")
    private List<Task> tasks;

    @ManyToOne(cascade = CascadeType.ALL)
    private Project project ;
}
