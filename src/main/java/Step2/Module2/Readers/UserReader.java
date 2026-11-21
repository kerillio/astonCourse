package Step2.Module2.Readers;

import Step2.Module2.Models.User;
import org.springframework.stereotype.Component;

import java.util.Scanner;

public class UserReader {

    public static User readByConsole() {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.println("Enter username: ");
        user.setName(readLine(sc));

        System.out.println("Enter email: ");
        user.setEmail(readLine(sc));

        System.out.println("Enter age: ");
        try {
            user.setAge(Integer.parseInt(readLine(sc)));
        } catch (NumberFormatException e) {
            user.setAge(0);
        }
        return user;

    }

    private static String readLine (Scanner sc) {
        boolean exit = false;
        String returnString = null;
        while (!exit) {
            try {
                returnString = sc.nextLine();
                if (returnString != null) exit = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnString;
    }

}
