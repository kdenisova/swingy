package com.kdenisov.swingy.model;

import java.util.ArrayList;

public class Hero {
    private final String name;
    private final HeroClass heroClass;
    private final int level;
    private final int experience;
    private final int attack;
    private final int defense;
    private final int hitPoints;
    private ArrayList<Artefact> artefacts;

    public Hero(String name, HeroClass heroClass, int level, int experience, int attack, int defense, int hitPoints) {
        this.name = name;
        this.heroClass = heroClass;
        this.level = level;
        this.experience = experience;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    public String getName() {
        return name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }


}
