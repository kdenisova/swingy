package com.kdenisov.swingy.model;

import java.util.ArrayList;

public class Dwarf extends Hero {
    public Dwarf(String name, HeroClass heroClass, int level, int experience, int attack, int defense, int hitPoints, ArrayList<Artefact> artefacts) {
        super(name, heroClass, level, experience, attack, defense, hitPoints, artefacts);
    }
}
