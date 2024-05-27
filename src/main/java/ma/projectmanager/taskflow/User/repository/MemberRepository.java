package ma.projectmanager.taskflow.User.repository;

import ma.projectmanager.taskflow.User.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
  Member findById(int id);
  Member  findByUsernameAndPassword(String username, String password);
}
