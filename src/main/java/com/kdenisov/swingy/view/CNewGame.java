package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.GameEngine;
import com.kdenisov.swingy.controller.Helper;
import com.kdenisov.swingy.controller.HibernateManager;
import com.kdenisov.swingy.model.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CNewGame {
    private HibernateManager hibernateManager;
    private Renderer renderer;
    private Scanner scanner;
    private Helper helper;

    @NotNull
    private String name;
    private HeroClass heroClass;
    private Artifact artifact;
    private int attack;
    private int defense;
    private int hitPoints;

    public void createHero(final HibernateManager hibernateManager, Renderer renderer) throws IOException {
        scanner = new Scanner(System.in);
        helper = new Helper();

        this.hibernateManager = hibernateManager;
        this.renderer = renderer;

        System.out.println("Let's create a new hero:");
        System.out.print("Name: ");
        name = scanner.next();

        heroClass = chooseHeroClass();
        artifact = chooseArtifact();
        calculateValues();
        //heroClass = null;
        saveHero();

        //scanner.close();
    }

    public void saveHero() {
        boolean status;

        status = hibernateManager.saveHero(
                name,
                heroClass,
                artifact,
                attack,
                defense,
                hitPoints,
                9 / 2,
                9 / 2);

        if (status) {
            System.out.println("A new hero created!");

            HeroEntity heroEntity = hibernateManager.getNewHero();
            Hero hero = HeroFactory.getInstance().buildHero(heroEntity);
            List<Artifact> artifacts = new ArrayList<>();
            for (ArtifactsEntity artifactEntity : heroEntity.getArtifacts()) {
                artifacts.add(artifactEntity.getArtifact());
            }

            hero.setArtifacts(artifacts);
            GameEngine gameEngine = new GameEngine(hibernateManager, renderer, hero);
            gameEngine.play();
        }
        else
        {
            System.out.println("Validation failed. See trace above.");
        }
    }

    public HeroClass chooseHeroClass() {
        HeroClass heroClass = null;
        String option = null;
        boolean selected = false;

        do {
            System.out.println("\nChoose a Hero Class:");
            System.out.println("             Attack   Defence");
            System.out.println("(1) Elf      " + helper.getAttack(HeroClass.Elf) + "      " + helper.getDefense(HeroClass.Elf));
            System.out.println("(2) Dwarf    " + helper.getAttack(HeroClass.Dwarf) + "      " + helper.getDefense(HeroClass.Dwarf));
            System.out.println("(3) Wizard   " + helper.getAttack(HeroClass.Wizard) + "      " + helper.getDefense(HeroClass.Wizard));
            System.out.print("> ");

            option = scanner.next();

            if (option.equals("1") || option.equals("2") || option.equals("3"))
                selected = true;
            else
                System.out.println("\n*** Unknown option! ***");
        } while (!selected);

        switch (option) {
            case "1":
                heroClass = HeroClass.Elf;
                break;
            case "2":
                heroClass = HeroClass.Dwarf;
                break;
            case "3":
                heroClass = HeroClass.Wizard;
                break;
        }

        return heroClass;
    }

    public Artifact chooseArtifact() {
        Artifact artifact = null;
        String option = null;
        boolean selected = false;

        do {
            System.out.println("\nChoose an Artifact:");
            System.out.println("(1) Weapon     +10 to Attack");
            System.out.println("(2) Armor      +10 to Defence");
            System.out.println("(3) Helm       +10 to Hit Points");
            System.out.print("> ");

            option = scanner.next();

            if (option.equals("1") || option.equals("2") || option.equals("3"))
                selected = true;
            else
                System.out.println("\n*** Unknown option! ***");
        } while (!selected);

        switch (option) {
            case "1":
                artifact = Artifact.Weapon;
                break;
            case "2":
                artifact = Artifact.Armor;
                break;
            case "3":
                artifact = Artifact.Helm;
                break;
        }

        return artifact;
    }

    public void calculateValues() {
        attack = helper.getAttack(heroClass);
        defense = helper.getDefense(heroClass);
        hitPoints = 100;

        switch (artifact) {
            case Weapon:
                attack += 10;
                break;
            case Armor:
                defense += 10;
                break;
            case Helm:
                hitPoints += 10;
                break;
            default:
                throw new IllegalStateException("Unexpected value of artifact: " + artifact);
        }
    }
}
