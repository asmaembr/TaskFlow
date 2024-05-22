package ma.projectmanager.taskflow.Notification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.projectmanager.taskflow.Task.model.Task;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Task task;

    private String description;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private LocalDateTime date;
}