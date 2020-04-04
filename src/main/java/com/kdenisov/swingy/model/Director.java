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
        builder.setHeroClass(HeroClass.Elf);
        builder.setLevel(heroEntity.getLevel());
        builder.setExperience(heroEntity.getExperience());
        builder.setAttack(heroEntity.getAttack());
        builder.setDefense(heroEntity.getDefense());
        builder.setHitPoints(heroEntity.getHitPoints());
        builder.setY(heroEntity.getY());
        builder.setX(heroEntity.getX());
        //builder.setArtefact(Artifact.Weapon);
        builder.setImage("/Users/angrynimfa/projects/swingy/src/main/resources/heroes/Elf.png");
    }

    public static void constructDwarf(Builder builder, HeroEntity heroEntity) {
        builder.setId(heroEntity.getId());
        builder.setName(heroEntity.getName());
        builder.setHeroClass(HeroClass.Dwarf);
        builder.setLevel(heroEntity.getLevel());
        builder.setExperience(heroEntity.getExperience());
        builder.setAttack(heroEntity.getAttack());
        builder.setDefense(heroEntity.getDefense());
        builder.setHitPoints(heroEntity.getHitPoints());
        builder.setY(heroEntity.getY());
        builder.setX(heroEntity.getX());
        //builder.setArtefact(Artifact.Helm);
        builder.setImage("/Users/angrynimfa/projects/swingy/src/main/resources/heroes/Dwarf.png");
    }

    public static void constructWizard(Builder builder, HeroEntity heroEntity) {
        builder.setId(heroEntity.getId());
        builder.setName(heroEntity.getName());
        builder.setHeroClass(HeroClass.Wizard);
        builder.setLevel(heroEntity.getLevel());
        builder.setExperience(heroEntity.getExperience());
        builder.setAttack(heroEntity.getAttack());
        builder.setDefense(heroEntity.getDefense());
        builder.setHitPoints(heroEntity.getHitPoints());
        builder.setY(heroEntity.getY());
        builder.setX(heroEntity.getX());
        //builder.setArtefact(Artifact.Armor);
        builder.setImage("/Users/angrynimfa/projects/swingy/src/main/resources/heroes/Wizard.png");
    }

//    public static void constructVillain(Builder builder, VillainEntity villainEntity) {
//        builder.setId(villainEntity.getId());
//        builder.setName(villainEntity.getName());
//        builder.setLevel(villainEntity.getLevel());
//        builder.setAttack(villainEntity.getAttack());
//        builder.setDefense(villainEntity.getDefense());
//        builder.setHitPoints(villainEntity.getHitPoints());
//        //builder.setX(villainEntity.getX());
//        //builder.setY(villainEntity.getY());
//        builder.setImage("/Users/angrynimfa/projects/swingy/src/main/resources/villains/" + villainEntity.getPicture());
//    }
}
