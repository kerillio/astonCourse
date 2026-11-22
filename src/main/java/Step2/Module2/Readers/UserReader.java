package Step2.Module2.Readers;

import Step2.Module2.DTO.UserCreateDto;
import Step2.Module2.Models.User;
import org.springframework.stereotype.Component;

import java.util.Scanner;

public class UserReader {

    public static UserCreateDto readByConsole() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter username: ");
        String name = readLine(sc);

        System.out.println("Enter email: ");
        String email = readLine(sc);

        System.out.println("Enter age: ");
        Integer age = 0;
        try {
            age = Integer.parseInt(readLine(sc));
        } catch (NumberFormatException e) {}

        return new UserCreateDto(name, email, age);

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
