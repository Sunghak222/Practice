package hk.edu.polyu.comp.comp2021.cvfs;

import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;
import hk.edu.polyu.comp.comp2021.cvfs.controller.*;
import hk.edu.polyu.comp.comp2021.cvfs.view.*;

import java.util.Scanner;

public class Application {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        CVFS cvfs = new CVFS();
        CLI cli = new CLI();
        view v = new view();
        // Initialize and utilize the system


        v.welcomeMessage();

        while (true) {
            System.out.print("$ ");
            String command = scanner.nextLine();
            String[] parts = command.split(" ");
            if (command.equals("exit")) break;
            cli.start(parts);
        }

    }
}
