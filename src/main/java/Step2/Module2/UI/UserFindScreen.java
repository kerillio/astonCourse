package Step2.Module2.UI;

import Step2.Module2.Models.User;
import Step2.Module2.Service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;


public class  UserFindScreen implements Screen {

    private static LinkedList<User> users = new LinkedList<>();

    @Override
    public Screen show(AnnotationConfigApplicationContext context) {

        users.clear();
        System.out.println("Выберите поле, по которому будем искать пользователей:");
        System.out.println("1. id");
        System.out.println("2. name");
        System.out.println("3. email");
        System.out.println("4. age");
        System.out.println("5. вывести всех пользователей на экран");
        System.out.println();
        System.out.println("0. exit");

        int choice = 0;
        boolean successRead = false;
        while (!successRead) {
            Scanner sc = new Scanner(System.in);
            try {
                choice = sc.nextInt();
                successRead = true;
            } catch (InputMismatchException e) {
                System.out.println("Введите число");
            }
        }

        switch (choice) {
            case 1: userFindById(context);
                break;
            case 2: userFindByName(context);
                break;
            case 3: userFindByEmail(context);
                break;
            case 4: userFindByAge(context);
                break;
            case 5: findAll(context);
                break;
            case 0: return context.getBean("mainScreen", Screen.class);
        }

        printUsers(users);

        return context.getBean("mainScreen", Screen.class);
    }

    private static void printUsers(LinkedList<User> users) {
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
            }
        } else System.out.println("Пользователей с таким полем не найдено");
        System.out.println("Press any key to continue...");
        new Scanner(System.in).nextLine();
    }

    private static void userFindById(AnnotationConfigApplicationContext context) {
        System.out.println("Введите Id:");
        boolean successRead = false;
        int id = 0;
        while (!successRead) {
            try {
                Scanner sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id > 0) successRead = true;
                else System.out.println("incorrect id");

            } catch (InputMismatchException e) {
                System.out.println("Введите число");
            }
        }


        users.addLast(context.getBean(UserService.class).findUserById(id));
    }

    private static void userFindByName(AnnotationConfigApplicationContext context) {
        System.out.println("Введите name:");
        boolean successRead = false;
        String name = null;
        while (!successRead) {
            try {
                Scanner sc = new Scanner(System.in);
                name = sc.nextLine();
                if (name.length() > 0) successRead = true;
                else System.out.println("incorrect name");
            } catch (NoSuchElementException e) {
                System.out.println("Пустая строка");
            }
        }

        for (User user : context.getBean(UserService.class).findUserByName(name)) users.addLast(user);
    }

    private static void userFindByEmail(AnnotationConfigApplicationContext context) {
        System.out.println("Введите email:");
        boolean successRead = false;
        String email = null;
        while (!successRead) {
            try {
                Scanner sc = new Scanner(System.in);
                email = sc.nextLine();
                if (email.length() > 0) successRead = true;
                else System.out.println("incorrect email");
            }  catch (NoSuchElementException e) {
                System.out.println("пустая строка");
            }
        }

        for (User user : context.getBean(UserService.class).findUserByEmail(email)) users.addLast(user);
    }

    private static void userFindByAge(AnnotationConfigApplicationContext context) {
        System.out.println("Введите age:");
        boolean successRead = false;
        int age = 0;
        while (!successRead) {
            try {
                Scanner sc = new Scanner(System.in);
                age = sc.nextInt();
                if (age > 0) successRead = true;
                else System.out.println("incorrect age");
            }  catch (InputMismatchException e) {
                System.out.println("Введите число");
            }
        }

        List<User> usersByAge = context.getBean(UserService.class).findUsersByAge(age);
        if (usersByAge != null) for (User user : usersByAge) users.addLast(user);
    }

    private static void findAll(AnnotationConfigApplicationContext context) {
        for (User user : context.getBean(UserService.class).findAll()) users.addLast(user);
    }

}
