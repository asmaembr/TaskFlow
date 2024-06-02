package ma.projectmanager.taskflow.Task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Notification.model.Notification;
import ma.projectmanager.taskflow.Objective.model.Objective;
import ma.projectmanager.taskflow.User.model.Member;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id ;
    private String description ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate ;

    private int priority ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Notification> notifications ;

    @Enumerated(EnumType.STRING)
    private TaskStatus status ;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Objective objective;


}
