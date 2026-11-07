package Step2.Module2.UI;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainScreen implements Screen {

    @Override
    public Screen show (){
        System.out.println("Выберите действие (введите цифру): ");
        System.out.println("1. Создать нового user, записать его в базу");
        System.out.println("2. Найти одного или несколько user (с выбором поля поиска)");
        System.out.println("3. Обновить данные user");
        System.out.println("4. Удалить user (по его id)");
        System.out.println();
        System.out.println("0. Выйти");


        boolean succesRead = false;
        Scanner sc = new Scanner(System.in);
        while (!succesRead) {
            try {
                switch (sc.nextInt()) {
                    case 1: return new CreateUserScreen();
                    case 2: return new UserFindScreen();
                    case 3: return new UserInfoUpdateScreen();
                    case 4: return new RemoveUserScreen();

                    case 0: return new ExitScreen();
                    default:
                        System.out.println("Некорректный ввод");;
                }
                succesRead = true;
            } catch (InputMismatchException e) {
                System.out.println("Введите число");
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
        return new MainScreen();
    }
}
