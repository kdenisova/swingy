package com.kdenisov.swingy.model;

import java.util.ArrayList;

public class Hobbit extends Hero {
    public Hobbit(String name, HeroClass heroClass, int level, int experience, int attack, int defense, int hitPoints, ArrayList<Artefact> artefacts) {
        super(name, heroClass, level, experience, attack, defense, hitPoints, artefacts);
    }
}
