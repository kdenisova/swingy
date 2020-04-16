package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.GameEngine;
import com.kdenisov.swingy.controller.HibernateManager;
import com.kdenisov.swingy.model.Villain;

import java.io.IOException;
import java.util.Scanner;

public class CRenderer implements Renderer {
    private final HibernateManager hibernateManager;
    private GameEngine game;

    public CRenderer(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    @Override
    public void renderMenu() throws IOException {
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
                System.out.println("Loading the list of Hero...");
                CContinue cContinue = new CContinue();
                cContinue.uploadHeroList(hibernateManager, this);
            case "3" :
                hibernateManager.tearDown();
                System.exit(0);
        }

        scanner.close();
    }

    @Override
    public void renderPlayground(GameEngine game, int mapSize) {
        this.game = game;

        System.out.print("\033\143");
        System.out.println("There will be playground soon");
        System.out.println(game.getHero().getName());
    }

    @Override
    public void updateGameAction(String str) {

    }

    @Override
    public void updateAttack(int attack) {

    }

    @Override
    public void updateDefense(int defense) {

    }

    @Override
    public void updateHitPoints(int hitPoints) {

    }

    @Override
    public void updateExperience(int experience) {

    }

    @Override
    public void updateArtifacts() {

    }

    @Override
    public int chooseAction(Villain villain) {
        int result = 0;

        return result;
    }

    @Override
    public void showMessageDialog(int flag, int val) {

    }

    @Override
    public void renderHero(int oldY, int oldX, int newY, int newX) {

    }

    @Override
    public void renderVillians() {

    }

    @Override
    public void renderObstacle() {

    }

    @Override
    public void removeVillain(int y, int x) {

    }
}
