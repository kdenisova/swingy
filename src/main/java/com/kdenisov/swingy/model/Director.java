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

    public static void constructElf(Builder builder, HeroEntity heroEntity) {
        builder.setName(heroEntity.getName());
        builder.setHeroClass(HeroClass.ELF);
        builder.setLevel(heroEntity.getLevel());
        builder.setExperience(heroEntity.getExperience());
        builder.setAttack(heroEntity.getAttack());
        builder.setDefense(heroEntity.getDefense());
        builder.setHitPoints(heroEntity.getHitPoints());
        builder.setArtefact(Artifact.WEAPON);
    }

    public static void constructDwarf(Builder builder, HeroEntity heroEntity) {
        builder.setName(heroEntity.getName());
        builder.setHeroClass(HeroClass.DWARF);
        builder.setLevel(heroEntity.getLevel());
        builder.setExperience(heroEntity.getExperience());
        builder.setAttack(heroEntity.getAttack());
        builder.setDefense(heroEntity.getDefense());
        builder.setHitPoints(heroEntity.getHitPoints());
        builder.setArtefact(Artifact.HELM);
    }

    public static void constructWizard(Builder builder, HeroEntity heroEntity) {
        builder.setName(heroEntity.getName());
        builder.setHeroClass(HeroClass.WIZARD);
        builder.setLevel(heroEntity.getLevel());
        builder.setExperience(heroEntity.getExperience());
        builder.setAttack(heroEntity.getAttack());
        builder.setDefense(heroEntity.getDefense());
        builder.setHitPoints(heroEntity.getHitPoints());
        builder.setArtefact(Artifact.ARMOR);
    }
}
