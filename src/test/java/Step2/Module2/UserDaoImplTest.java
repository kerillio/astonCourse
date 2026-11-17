package Step2.Module2;
import Step2.Module2.DAO.UserDao;
import Step2.Module2.DAO.UserDaoImpl;
import Step2.Module2.Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
public class UserDaoImplTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    private static SessionFactory sessionFactory;
    private UserDao userDao;

    @BeforeAll
    static void setupHibernate() {
        Configuration configuration = new Configuration();

        // Передаем настройки подключения из Testcontainers
        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        // Автоматическое создание таблиц по Entity-классам для тестов
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.setProperty("hibernate.show_sql", "true");

        // Регистрируем ваши @Entity классы
        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    @AfterAll
    static void tearDownHibernate() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @BeforeEach
    void setUp() {
        // Очищаем таблицу перед каждым тестом для полной изоляции
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createMutationQuery("DELETE FROM User").executeUpdate();
            tx.commit();
        }
        // Инициализируем DAO, передавая ему фабрику сессий
        userDao = new UserDaoImpl();
    }

    @Test
    void saveAndFindUser() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setName("Anna");
        user.setEmail("anna@example.com");
        userDao.save(user);
        User foundUser = userDao.findById(user.getId());
        assertThat(foundUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    void saveAndUpdateUser() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setName("kirill");
        user.setEmail("kirill@example.com");
        user.setAge(30);

        //Сохраняем пользователя
        userDao.save(user);

        //проверка сохранения
        User foundUser = userDao.findById(user.getId());
        assertThat(foundUser).usingRecursiveComparison().isEqualTo(user);

        //обновляем данные
        user.setName("alex");
        userDao.update(user);

        //проверяем что данные обновлены
        User foundUserUpdated = userDao.findById(user.getId());
        Assertions.assertNotEquals(foundUser, foundUserUpdated);
        assertThat(foundUserUpdated).usingRecursiveComparison().isEqualTo(user);

    }
}
