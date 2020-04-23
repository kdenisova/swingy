package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.GameEngine;
import com.kdenisov.swingy.controller.HibernateManager;
import com.kdenisov.swingy.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLIContinue {
    private HibernateManager hibernateManager;
    private Renderer renderer;
    private Scanner scanner;
    private List<HeroEntity> heroEntities;

    public void uploadHeroList(final HibernateManager hibernateManager, Renderer renderer) {
        this.hibernateManager = hibernateManager;
        this.renderer = renderer;
        heroEntities = hibernateManager.getListHeroes();

        scanner = new Scanner(System.in);

        System.out.print("\033\143");
        System.out.println("Loading the list of Hero...");

        if (heroEntities.size() == 0) {
            showMessage();
            System.out.println("\nPress any key to return to Main Menu.");

            scanner.next();
            renderer.renderMenu();
        }
        else
            showHeroList();
    }

    public void showMessage() {
        System.out.println("\nNo saved games found!");
    }

    public void showHeroList() {
        boolean selected = false;
        String scan;
        int id = -1;

        do {
            System.out.print("\033\143");
            System.out.println("Choose a hero: ");

            for (int i = 0; i < heroEntities.size(); i++) {
                System.out.println("(" + i + ") " + heroEntities.get(i).getName() +
                        " (Class: " + heroEntities.get(i).getHeroClass() +
                        ", Level: " + heroEntities.get(i).getLevel() +
                        ", Experience: " + heroEntities.get(i).getExperience() + ")");
            }

            System.out.println("\nEnter number of hero or: (B) to Main menu, (G) to GUI view, (X) to Exit\n");
            System.out.print("> ");
            scan = scanner.next();

            if (scan.toLowerCase().equals("b") || scan.toLowerCase().equals("g") || scan.toLowerCase().equals("x")) {
                chooseDefaultOption(scan);
                return;
            }

            try {
                id = Integer.parseInt(scan);
            } catch (NumberFormatException e) {
                System.out.println("\n*** Unknown option! ***");
            }

            if (id >=0 && id < heroEntities.size())
                selected = true;
        } while (!selected);

        startGame(heroEntities.get(id));
    }

    public void startGame(HeroEntity heroEntity) {

        Hero hero = HeroFactory.getInstance().buildHero(heroEntity);

        List<Artifact> artifacts = new ArrayList<>();
        for (ArtifactsEntity artifactEntity : heroEntity.getArtifacts()) {
            artifacts.add(artifactEntity.getArtifact());
        }

        hero.setArtifacts(artifacts);
        GameEngine gameEngine = new GameEngine(hibernateManager, renderer, hero);
        gameEngine.continueGame();
    }

    public void chooseDefaultOption(String option) {
        switch (option) {
            case "b":
                renderer.renderMenu();
                break;
            case "g":
                System.out.print("\033\143");
                renderer = new GUIRenderer(hibernateManager);
                GUIContinue guiContinue = new GUIContinue();
                guiContinue.uploadHeroList(hibernateManager, renderer);
                break;
            case "x":
                hibernateManager.tearDown();
                scanner.close();
                System.exit(0);
                break;
        }
    }
}
