package Step2.Module2;

import Step2.Module2.Models.User;
import Step2.Module2.Readers.UserReader;
import Step2.Module2.Service.UserService;
import Step2.Module2.UI.CreateUserScreen;
import Step2.Module2.UI.MainScreen;
import Step2.Module2.UI.*;
import jakarta.persistence.EntityManager;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static LinkedList<Screen> screenHistory = new LinkedList<>();


    static void main(String[] args) {
        screenHistory.addLast(new MainScreen());
        boolean exit = false;
        while (!exit) {
            if (screenHistory.getLast().getClass().equals(ExitScreen.class)) {
                exit = true;
                break;
            } else screenHistory.addLast(screenHistory.getLast().show());


        }
    }

}

