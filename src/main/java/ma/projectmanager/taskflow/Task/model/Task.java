package ma.projectmanager.taskflow.Task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Notification.model.Notification;
import ma.projectmanager.taskflow.User.model.Member;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
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
    private LocalDate startDate ;
    private LocalDate endDate ;
    private int priority ;

    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "task")
    private List<Notification> notifications ;

    @Enumerated(EnumType.STRING)
    private TaskStatus status ;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Member>members;


}
