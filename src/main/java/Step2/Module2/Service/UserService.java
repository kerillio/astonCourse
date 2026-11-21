package Step2.Module2.Service;

import Step2.Module2.Models.User;
import Step2.Module2.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser() {
        return new User();
    }

    @Transactional
    public void addUser(User user) {
        if (user.getName().isEmpty()) {
            log.warn("user.name is empty");
        } else if (user.getAge() < 0) {
            log.warn("Age is incorrect");
        } else if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            log.warn("Email is incorrect");
        } else {
            userRepository.save(user);
        }

    }

    public void updateUser (User user) {
        if (user.getName().isEmpty()) {
            log.warn("Name is empty");
        } else if (user.getAge() < 0) {
            log.warn("Age is incorrect");
        } else if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            log.warn("Email is incorrect");
        } else {
            userRepository.save(user);
        }
    }

    public void removeUserById(int id) {
        if (id > 0) {
            userRepository.deleteById((long) id);
        }
    }

    public  List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
        if (id > 0) {
            Optional<User> foundUser = userRepository.findById((long) id);
            if (foundUser.isPresent()) return foundUser.get();
            else return null;
        } else return null;
    }

    public List<User> findUserByName(String name) {
        if (name.isEmpty()) {
            log.warn("name is empty");
            return null;
        } else return userRepository.findByName(name);
    }

    public List<User> findUserByEmail(String email) {
        if (email.isEmpty()) {
            log.warn("email is empty");
            return null;
        } else return userRepository.findByEmail(email);
    }

    public List<User> findUsersByAge(int age) {
        if (age < 1) {
            log.warn("Age is incorrect");
            return null;
        } else return userRepository.findUserByAge(age);
    }

}
