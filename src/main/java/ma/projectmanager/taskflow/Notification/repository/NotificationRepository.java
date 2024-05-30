package ma.projectmanager.taskflow.Notification.repository;

import ma.projectmanager.taskflow.Notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    Notification findById(int id);
    Notification deleteById(int id);
}
