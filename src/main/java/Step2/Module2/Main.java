package Step2.Module2;

import Step2.Module2.DAO.HibernateSessionFactoryUtil;
import Step2.Module2.UI.*;
import Step2.Module2.Config.AppConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedList;

public class Main {
    private static LinkedList<Screen> screenHistory = new LinkedList<>();


    static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        screenHistory.addLast(context.getBean("mainScreen", Screen.class));
        boolean exit = false;
        while (!exit) {
            if (screenHistory.getLast().equals(context.getBean("exitScreen", Screen.class))) {
                HibernateSessionFactoryUtil.shutdown();
                context.close();
                exit = true;
            } else screenHistory.addLast(screenHistory.getLast().show(context));
        }
    }

}

