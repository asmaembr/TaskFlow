package ma.projectmanager.taskflow.Project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.User.model.Manager;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @ManyToOne
    private Manager manager ;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Objective>objectiveList ;

    private String description ;
    private LocalDate startDate ;
    private LocalDate endDate ;


}
