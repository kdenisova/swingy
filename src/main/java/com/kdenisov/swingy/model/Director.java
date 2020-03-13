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
        builder.setId(heroEntity.getId());
        builder.setName(heroEntity.getName());
        builder.setHeroClass(HeroClass.ELF);
        builder.setLevel(heroEntity.getLevel());
        builder.setExperience(heroEntity.getExperience());
        builder.setAttack(heroEntity.getAttack());
        builder.setDefense(heroEntity.getDefense());
        builder.setHitPoints(heroEntity.getHitPoints());
        builder.setX(heroEntity.getX());
        builder.setY(heroEntity.getY());
        builder.setArtefact(Artifact.WEAPON);
        builder.setHeroImage("/Users/angrynimfa/projects/swingy/src/main/resources/heroes/elf.png");
    }

    public static void constructDwarf(Builder builder, HeroEntity heroEntity) {
        builder.setId(heroEntity.getId());
        builder.setName(heroEntity.getName());
        builder.setHeroClass(HeroClass.DWARF);
        builder.setLevel(heroEntity.getLevel());
        builder.setExperience(heroEntity.getExperience());
        builder.setAttack(heroEntity.getAttack());
        builder.setDefense(heroEntity.getDefense());
        builder.setHitPoints(heroEntity.getHitPoints());
        builder.setX(heroEntity.getX());
        builder.setY(heroEntity.getY());
        builder.setArtefact(Artifact.HELM);
        builder.setHeroImage("/Users/angrynimfa/projects/swingy/src/main/resources/heroes/dwarf.png");
    }

    public static void constructWizard(Builder builder, HeroEntity heroEntity) {
        builder.setId(heroEntity.getId());
        builder.setName(heroEntity.getName());
        builder.setHeroClass(HeroClass.WIZARD);
        builder.setLevel(heroEntity.getLevel());
        builder.setExperience(heroEntity.getExperience());
        builder.setAttack(heroEntity.getAttack());
        builder.setDefense(heroEntity.getDefense());
        builder.setHitPoints(heroEntity.getHitPoints());
        builder.setX(heroEntity.getX());
        builder.setY(heroEntity.getY());
        builder.setArtefact(Artifact.ARMOR);
        builder.setHeroImage("/Users/angrynimfa/projects/swingy/src/main/resources/heroes/wizard.png");
    }
}
