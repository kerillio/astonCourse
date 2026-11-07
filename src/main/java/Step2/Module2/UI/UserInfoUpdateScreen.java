package Step2.Module2.UI;

import Step2.Module2.Models.User;
import Step2.Module2.Service.UserService;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInfoUpdateScreen implements Screen {

    @Override
    public Screen show() {

        System.out.println("Введите id пользователя, информацию о котором надо изменить:");
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

        User user = UserService.findUserById(id);

        System.out.println("Найден:");
        System.out.println(user);
        System.out.println("\nВыберите поле, которое будет меняться:");
        System.out.println("1. Имя");
        System.out.println("2. Почта");


        int choice = 0;
        successRead = false;
        while (!successRead) {
            Scanner sc = new Scanner(System.in);
            try {
                choice = sc.nextInt();
                if  (choice == 1 || choice == 2) {
                    successRead = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите число");
            }
        }

        if  (choice == 1) {
            nameUpdate(user);
        } else if (choice == 2) {
            emailUpdate(user);
        }
        UserService.updateUser(user);

        return new MainScreen();
    }

    private void emailUpdate(User user) {
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
        user.setEmail(email);

    }

    private void nameUpdate(User user) {
        System.out.println("Введите name:");
        boolean successRead = false;
        String name = null;
        while (!successRead) {
            try {
                Scanner sc = new Scanner(System.in);
                name = sc.nextLine();
                if (name.length() > 0) successRead = true;
                else System.out.println("incorrect name");
            }  catch (NoSuchElementException e) {
                System.out.println("пустая строка");
            }
        }
        user.setName(name);
    }

}
