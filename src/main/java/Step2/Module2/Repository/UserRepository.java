package Step2.Module2.Repository;

import Step2.Module2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {


    List<User> findByEmail(String email);

    List<User> findByName(String name);

    List<User> findByAge(int age);
}
