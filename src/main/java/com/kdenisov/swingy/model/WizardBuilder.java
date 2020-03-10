package com.kdenisov.swingy.model;

import java.util.ArrayList;

public class WizardBuilder implements Builder {
    private String name;
    private HeroClass heroClass;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;
    private ArrayList<Artifact> artifacts = new ArrayList<Artifact>();
    private String heroImage;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public void setArtefact(Artifact artifact) {
        this.artifacts.add(artifact);
    }

    @Override
    public void setHeroImage(String heroImage) {
        this.heroImage = heroImage;
    }

    public Wizard getResult() {
        return new Wizard(name, heroClass, level, experience, attack, defense, hitPoints, artifacts, heroImage);
    }
}
