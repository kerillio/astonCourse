package Step2.Module2;

import Step2.Module2.UI.*;
import Step2.Module2.Config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedList;

@SpringBootApplication
public class Main {
    private static LinkedList<Screen> screenHistory = new LinkedList<>();


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        screenHistory.addLast(context.getBean("mainScreen", Screen.class));
        boolean exit = false;
        while (!exit) {
            if (screenHistory.getLast().equals(context.getBean("exitScreen", Screen.class))) {
                context.close();
                exit = true;
            } else screenHistory.addLast(screenHistory.getLast().show(context));
        }
    }

}

