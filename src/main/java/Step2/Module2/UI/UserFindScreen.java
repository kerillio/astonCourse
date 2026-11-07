package Step2.Module2.UI;

import Step2.Module2.Models.User;
import Step2.Module2.Service.UserService;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserFindScreen implements Screen {

    private static LinkedList<User> users = new LinkedList<>();

    @Override
    public Screen show() {

        users.clear();
        System.out.println("Выберите поле, по которому будем искать пользователей:");
        System.out.println("1. id");
        System.out.println("2. name");
        System.out.println("3. email");
        System.out.println("4. age");
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
            case 1: userFindById();
                break;
            case 2: userFindByName();
                break;
            case 3: userFindByEmail();
                break;
            case 4: userFindByAge();
                break;
            case 0: return new MainScreen();
        }

        printUsers(users);

        return new MainScreen();
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

    private static void userFindById() {
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

        users.addLast(UserService.findUserById(id));
    }

    private static void userFindByName() {
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

        for (User user : UserService.findUserByName(name)) users.addLast(user);
    }

    private static void userFindByEmail() {
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

        for (User user : UserService.findUserByEmail(email)) users.addLast(user);
    }

    private static void userFindByAge() {
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

        for (User user : UserService.findUsersByAge(age)) users.addLast(user);
    }

}
