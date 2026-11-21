package Step2.Module2.UI;

import Step2.Module2.DTO.UserUpdateDto;
import Step2.Module2.DTO.UserViewDto;
import Step2.Module2.Models.User;
import Step2.Module2.Service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;


public class UserInfoUpdateScreen implements Screen {

    @Override
    public Screen show(ConfigurableApplicationContext context) {

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

        Optional<UserViewDto> foundUser = Optional.ofNullable(context.getBean(UserService.class).findUserById(id));

        if (foundUser.isEmpty()) System.out.println("User not found");
        else {
            System.out.println("Найден:");
            System.out.println(foundUser);
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
                String newName = nameUpdate();
                context.getBean(UserService.class).updateUser(
                        new UserUpdateDto(
                                foundUser.get().id(),
                                newName,
                                foundUser.get().email(),
                                foundUser.get().age(),
                                foundUser.get().created_at()
                        )
                );
            } else if (choice == 2) {
                String newEmail = emailUpdate();
                context.getBean(UserService.class).updateUser(
                        new UserUpdateDto(
                                foundUser.get().id(),
                                foundUser.get().name(),
                                newEmail,
                                foundUser.get().age(),
                                foundUser.get().created_at()
                        )
                );

            }
        }

        System.out.println();

        return context.getBean("mainScreen", Screen.class);
    }

    private String emailUpdate() {
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

        return email;

    }

    private String nameUpdate() {
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
        return name;
    }

}
