package com.kdenisov.swingy.model;

public class Director {
    public void constructElf(Builder builder) {
        builder.setName("Legolas");
        builder.setHeroClass(HeroClass.Elf);
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(100);
        builder.setDefense(10);
        builder.setHitPoints(5);
    }
}
