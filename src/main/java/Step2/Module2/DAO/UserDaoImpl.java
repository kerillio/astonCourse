package Step2.Module2.DAO;

import Step2.Module2.Models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao<User> {

    private static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
    private static SessionFactory sessionFactory;


    public UserDaoImpl () {
        this.sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    public UserDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            log.info("User added successfully");
        } catch (Exception e) {
            log.error("Error in method save(user): ", e);
            log.warn("transaction rolled back");
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User findById(int id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        User user = new User();
        try {
            user = entityManager.find(User.class, id);
        } catch (Exception e) {
            log.error("error in method findById: ", e);
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public List findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<User> users = new ArrayList<>();
        String query = "FROM User";
        try {
            Query query1 = entityManager.createQuery(query);
            users.addAll(query1.getResultList());
        } catch (IllegalArgumentException e) {
            log.error("Неверный запрос hql для findAll():", e);
        } finally {
            entityManager.close();
        }
        return users;
    }

    public List<User> findByName(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        LinkedList<User> users = new LinkedList<>();
        String query = "FROM User u WHERE u.name LIKE :name";
        try {
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("name", name);
            for(Object o : query1.getResultList()) {
                users.add((User)o);
            }
        }  catch (Exception e) {
            log.error("error in method findByName: ", e);
        } finally {
            entityManager.close();
        }
        return users;
    }

    public List<User> findByEmail(String email) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        log.info("entity manager created");
        LinkedList<User> users = new LinkedList<>();
        String query = "FROM User u WHERE u.email = :email";
        try {
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            for(Object o : query1.getResultList()) {
                users.add((User)o);
            }
        }   catch (Exception e) {
            log.error("error in method findByEmail: ", e);
        } finally {
            entityManager.close();
        }
        return users;
    }

    public List<User> findUserByAge (int age) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<User> users = new ArrayList<>();
        String query = "FROM User u WHERE u.age = :age";
        try {
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("age", age);
            for(Object o : query1.getResultList()) {
                users.add((User)o);
            }
        }   catch (Exception e) {
            log.error("error in method findByAge: ", e);
        } finally {
            entityManager.close();
        }
        return users;
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        String query = "update User"
                + " set email = :email,"
                + " name = :name,"
                + " age = :age"
                + " where id = :id";

        try {
            entityManager.getTransaction().begin();
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("id", user.getId());
            query1.setParameter("email", user.getEmail());
            query1.setParameter("name", user.getName());
            query1.setParameter("age", user.getAge());
            query1.executeUpdate();
            entityManager.getTransaction().commit();
            log.info("user updated successfully");
        }  catch (Exception e) {
            log.error("error in method update(user): ", e);
            entityManager.getTransaction().rollback();
            log.info("transaction rolled back");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        String query = "DELETE FROM User u WHERE u.id = :id";

        try {
            entityManager.getTransaction().begin();
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("id", id);
            query1.executeUpdate();
            entityManager.getTransaction().commit();
            log.info("user removed successfully");
        } catch (Exception e) {
            log.error("error in method delete(user): ", e);
            entityManager.getTransaction().rollback();
            log.info("transaction rolled back");
        } finally {
            entityManager.close();
        }
    }
}
