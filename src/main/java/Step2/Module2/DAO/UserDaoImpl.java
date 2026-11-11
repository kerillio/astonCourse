package Step2.Module2.DAO;

import Step2.Module2.Models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(User user) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            System.out.println("User added successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User findById(int id) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        User user = new User();
        try {
            entityManager.getTransaction().begin();
            user = entityManager.find(User.class, id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public List findAll() {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        List<User> users = new ArrayList<>();
        String query = "FROM User u";
        try {
            entityManager.getTransaction().begin();
            Query query1 = entityManager.createQuery(query);
            users.addAll(query1.getResultList());
        } catch (IllegalArgumentException e) {
            System.out.printf("Неверный запрос hql для UserDaoImpl.findAll()");
        } finally {
            entityManager.close();
        }
        return List.of();
    }

    public List<User> findByName(String name) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        LinkedList<User> users = new LinkedList<>();
        String query = "FROM User u WHERE u.name LIKE :name";
        try {
            entityManager.getTransaction().begin();
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("name", name);
            for(Object o : query1.getResultList()) {
                users.add((User)o);
            }
        }  catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            entityManager.close();
        }
        return users;
    }

    public List<User> findByEmail(String email) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        LinkedList<User> users = new LinkedList<>();
        String query = "FROM User u WHERE u.email = :email";
        try {
            entityManager.getTransaction().begin();
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            for(Object o : query1.getResultList()) {
                users.add((User)o);
            }
        }   catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            entityManager.close();
        }
        return users;
    }

    public List<User> findUserByAge (int age) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        List<User> users = new ArrayList<>();
        String query = "FROM User u WHERE u.age = :age";
        try {
            entityManager.getTransaction().begin();
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("age", age);
            for(Object o : query1.getResultList()) {
                users.add((User)o);
            }
        }   catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            entityManager.close();
        }
        return users;
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
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
            System.out.println("User updated successfully");
        }  catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        String query = "DELETE FROM User u WHERE u.id = :id";

        try {
            entityManager.getTransaction().begin();
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("id", id);
            query1.executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("User removed successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
