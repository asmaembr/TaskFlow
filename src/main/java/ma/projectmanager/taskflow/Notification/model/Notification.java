package ma.projectmanager.taskflow.Notification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Task.model.Task;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Task task;

    private String description;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Notification(Task task, String description, NotificationType type, Date date) {
        this.task = task;
        this.description = description;
        this.type = type;
        this.date = date;
    }
}
