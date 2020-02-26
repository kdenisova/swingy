package com.kdenisov.swingy.model;

public class Director {

    private static Director director;

    private Director() {

    }

    public static Director getDirector() {
        if (director == null) {
            director = new Director();
        }

        return director;
    }

    public static void constructElf(Builder builder, String name) {
        builder.setName(name);
        builder.setHeroClass(HeroClass.ELF);
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(100);
        builder.setDefense(10);
        builder.setHitPoints(5);
        builder.setArtefact(Artefact.WEAPON);
    }

    public static void constructDwarf(Builder builder, String name) {
        builder.setName(name);
        builder.setHeroClass(HeroClass.DWARF);
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(110);
        builder.setDefense(30);
        builder.setHitPoints(10);
        builder.setArtefact(Artefact.HELM);
    }

    public static void constructHobbit(Builder builder, String name) {
        builder.setName(name);
        builder.setHeroClass(HeroClass.WIZARD);
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(50);
        builder.setDefense(20);
        builder.setHitPoints(10);
        builder.setArtefact(Artefact.ARMOR);
    }
}
