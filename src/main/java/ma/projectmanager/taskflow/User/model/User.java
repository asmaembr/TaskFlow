package ma.projectmanager.taskflow.User.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role" , length = 3, discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String username;
    protected String password;
    protected String fullName;
    protected String tel ;

    protected LocalDate birthDay ;
    protected String email;

    @Column(insertable=false, updatable=false)
    protected String role;

    @CreationTimestamp
    protected LocalDateTime inscriptionDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    protected Sex sex ;

    @Enumerated(EnumType.STRING)
    protected Skill skill;


}
