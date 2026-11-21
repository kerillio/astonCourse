package Step2.Module2.UI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class MainScreen implements Screen {

    @Override
    public Screen show (AnnotationConfigApplicationContext context){
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
                    case 1: return context.getBean("createUserScreen", Screen.class);
                    case 2: return  context.getBean("userFindScreen", Screen.class);
                    case 3: return  context.getBean("userInfoUpdateScreen", Screen.class);
                    case 4: return  context.getBean("removeUserScreen", Screen.class);

                    case 0: return  context.getBean("exitScreen", Screen.class);
                    default:
                        System.out.println("Некорректный ввод");;
                }
                succesRead = true;
            } catch (InputMismatchException e) {
                System.out.println("Введите число");
            } catch (NoSuchElementException e) {
                System.out.println("No such element exception");;
            }
        }
        return  context.getBean("mainScreen", Screen.class);
    }
}
