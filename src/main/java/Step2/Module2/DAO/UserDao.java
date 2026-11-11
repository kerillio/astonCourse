package Step2.Module2.DAO;

import Step2.Module2.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao<T>{
    void save(User user);
    T findById(int id);
    List<T> findAll();
    void update(User user);
    void delete(int id);
}
