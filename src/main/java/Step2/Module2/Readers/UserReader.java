package Step2.Module2.Readers;

import Step2.Module2.Models.User;

import java.util.Scanner;

public class UserReader {

    public static User readByConsole() {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        try {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            user.setName(username);
            System.out.println("Enter email: ");
            String email = sc.nextLine();
            user.setEmail(email);
            System.out.println("Enter age: ");
            int age = sc.nextInt();
            user.setAge(age);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;

    }

}
