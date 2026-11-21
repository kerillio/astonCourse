package Step2.Module2.DAO;

import Step2.Module2.Models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class HibernateSessionFactoryUtil {

    private static final Logger log = LoggerFactory.getLogger(HibernateSessionFactoryUtil.class);
    private static SessionFactory sessionFactory = buildSessionFactory();

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory buildSessionFactory() {
            try {
                log.info("building new SessionFactory");
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                log.info("built new SessionFactory");
            } catch (Exception e) {
                log.error("error initializing SessionFactory: ", e);
                e.printStackTrace();
            }

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void shutdown() {
            log.info("closing SessionFactory");
            sessionFactory.close();
            log.info("SessionFactory closed");
    }


}
