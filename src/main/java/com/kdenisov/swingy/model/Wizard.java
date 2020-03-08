package com.kdenisov.swingy.model;

import java.util.ArrayList;

public class Wizard extends Hero {
    public Wizard(String name, HeroClass heroClass, int level, int experience, int attack, int defense, int hitPoints, ArrayList<Artifact> artifacts) {
        super(name, heroClass, level, experience, attack, defense, hitPoints, artifacts);
    }
}
