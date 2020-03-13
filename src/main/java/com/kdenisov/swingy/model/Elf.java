package com.kdenisov.swingy.model;

import java.util.ArrayList;

public class Elf extends Hero {

    public Elf(int id, String name, HeroClass heroClass, int level, int experience, int attack, int defense, int hitPoints,
               ArrayList<Artifact> artifacts, String heroImage, int x, int y) {
        super(id, name, heroClass, level, experience, attack, defense, hitPoints, artifacts, heroImage, x, y);
    }
}
