package Step2.Module2.Service;


import Step2.Module2.Models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.LinkedList;

public class UserService {

    public UserService() {    }

    public User getUser() {
        return new User();
    }

    public static void addUser(User user) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("myHibernateUnit").createEntityManager();
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

    public static void updateUser (User user) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("myHibernateUnit").createEntityManager();
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
        }  finally {
            entityManager.close();
        }
    }

    public static void removeUserById(int id) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("myHibernateUnit").createEntityManager();
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
        }  finally {
            entityManager.close();
        }
    }

    public static User findUserById(int id) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("myHibernateUnit").createEntityManager();
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

    public static LinkedList<User> findUserByName(String name) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("myHibernateUnit").createEntityManager();
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

    public static LinkedList<User> findUserByEmail(String email) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("myHibernateUnit").createEntityManager();
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

    public static LinkedList<User> findUsersByAge(int age) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("myHibernateUnit").createEntityManager();
        LinkedList<User> users = new LinkedList<>();
        String query = "FROM User u WHERE u.age = :age";
        try {
            entityManager.getTransaction().begin();
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("age", age);
            for(Object o : query1.getResultList()) {
                users.add((User)o);
            }
        }    catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            entityManager.close();
        }
        return users;
    }

}
