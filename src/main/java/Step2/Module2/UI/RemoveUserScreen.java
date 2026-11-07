package Step2.Module2.UI;

import Step2.Module2.Models.User;
import Step2.Module2.Service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RemoveUserScreen implements Screen {


    @Override
    public Screen show() {
        System.out.println("Введите id, по которому будет удален user");
        boolean success = false;
        while (!success) {
            try {
                Scanner scanner = new Scanner(System.in);
                int userId = scanner.nextInt();
                if (userId > 0)
                {
                    success = true;
                    UserService.removeUserById(userId);
                }

            } catch(InputMismatchException e) {
                System.out.println("Введите число");
            }
        }


        return new MainScreen();
    }
}
