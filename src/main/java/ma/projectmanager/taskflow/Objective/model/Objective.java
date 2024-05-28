package ma.projectmanager.taskflow.Objective.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Project.model.Project;
import ma.projectmanager.taskflow.Task.model.Task;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate ;
    private String description ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objective")
    private List<Task> tasks;

    @ManyToOne
    private Project project ;
}
