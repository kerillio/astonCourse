package Step2.Module2.Service;

import Step2.Module2.DAO.UserDaoImpl;
import Step2.Module2.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private static UserDaoImpl userDao = new UserDaoImpl();

    public UserService() {
    }

    public User getUser() {
        return new User();
    }

    public static void addUser(User user) {
        if (user.getName().isEmpty()) {
            log.warn("user.name is empty");
        } else if (user.getAge() < 0) {
            log.warn("Age is incorrect");
        } else if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            log.warn("Email is incorrect");
        } else {
            userDao.save(user);
        }

    }

    public static void updateUser (User user) {
        if (user.getName().isEmpty()) {
            log.warn("Name is empty");
        } else if (user.getAge() < 0) {
            log.warn("Age is incorrect");
        } else if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            log.warn("Email is incorrect");
        } else {
            userDao.update(user);
        }
    }

    public static void removeUserById(int id) {
        if (id > 0) {
            userDao.delete(id);
        }
    }

    public static User findUserById(int id) {
        if (id > 0) {
            return userDao.findById(id);
        } else return null;
    }

    public static List<User> findUserByName(String name) {
        if (name.isEmpty()) {
            log.warn("name is empty");
            return null;
        } else return userDao.findByName(name);
    }

    public static List<User> findUserByEmail(String email) {
        if (email.isEmpty()) {
            log.warn("email is empty");
            return null;
        } else return userDao.findByEmail(email);
    }

    public static List<User> findUsersByAge(int age) {
        if (age < 1) {
            log.warn("Age is incorrect");
            return null;
        } else return userDao.findUserByAge(age);
    }

}
