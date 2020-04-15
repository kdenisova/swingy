package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.HibernateManager;

import java.io.IOException;
import java.util.Scanner;

public class CRenderer implements Renderer {

    @Override
    public void renderMenu(HibernateManager hibernateManager) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String option;
        boolean selected = false;

        System.out.print("\033\143");

        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("*                                                 *");
        System.out.println("*                                                 *");
        System.out.println("*                Welcome to Swingy                *");
        System.out.println("*                                                 *");
        System.out.println("*                                                 *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println();

        do {
            System.out.println("Choose an option:");
            System.out.println("(1) New Game");
            System.out.println("(2) Continue");
            System.out.println("(3) Exit");
            System.out.print("> ");

            option = scanner.next();

            if (option.equals("1") || option.equals("2") || option.equals("3"))
                selected = true;
            else
                System.out.println("\n*** Unknown option! ***");
        } while (!selected);

        System.out.print("\033\143");
        switch (option) {
            case "1" :
                System.out.println("Starting a new game...");
                CNewGame newGame = new CNewGame();
                newGame.createHero(hibernateManager, this);

            case "2" :
                //continue
            case "3" :
                hibernateManager.tearDown();
                System.exit(0);
        }

        scanner.close();
    }
}
