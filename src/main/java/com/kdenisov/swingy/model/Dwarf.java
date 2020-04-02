package com.kdenisov.swingy.model;

import java.util.ArrayList;

public class Dwarf extends Hero {
    public Dwarf(int id, String name, HeroClass heroClass, int level, int experience, int attack, int defense, int hitPoints,
                 ArrayList<Artifact> artifacts, String heroImage, int y, int x) {
        super(id, name, heroClass, level, experience, attack, defense, hitPoints, artifacts, heroImage, y, x);
    }
}
