package Step2.Module2.Service;

import Step2.Module2.DAO.UserDao;
import Step2.Module2.DAO.UserDaoImpl;
import Step2.Module2.Models.User;

import java.util.LinkedList;
import java.util.List;

public class UserService {

    private static UserDaoImpl userDao = new UserDaoImpl();

    public UserService() {
    }

    public User getUser() {
        return new User();
    }

    public static void addUser(User user) {
        if (user.getName().isEmpty()) {
            System.out.printf("Name is empty");
        } else if (user.getAge() < 0) {
            System.out.printf("Age is incorrect");
        } else if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            System.out.printf("Email is incorrect");
        } else {
            userDao.save(user);
        }

    }

    public static void updateUser (User user) {
        if (user.getName().isEmpty()) {
            System.out.printf("Name is empty");
        } else if (user.getAge() < 0) {
            System.out.printf("Age is incorrect");
        } else if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            System.out.printf("Email is incorrect");
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
            System.out.printf("name is empty");
            return null;
        } else return userDao.findByName(name);
    }

    public static List<User> findUserByEmail(String email) {
        if (email.isEmpty()) {
            System.out.printf("email is empty");
            return null;
        } else return userDao.findByEmail(email);
    }

    public static List<User> findUsersByAge(int age) {
        if (age < 1) {
            System.out.printf("Age is incorrect");
            return null;
        } else return userDao.findUserByAge(age);
    }

}
