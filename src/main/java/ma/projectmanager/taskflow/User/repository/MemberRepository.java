package ma.projectmanager.taskflow.User.repository;

import ma.projectmanager.taskflow.User.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
