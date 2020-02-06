package com.kdenisov.swingy.model;

public class Director {
    public void constructElf(Builder builder, String name) {
        builder.setName(name);
        builder.setHeroClass(HeroClass.ELF);
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(100);
        builder.setDefense(10);
        builder.setHitPoints(5);
        builder.setArtefact(Artefact.WEAPON);
    }

    public void constructDwarf(Builder builder, String name) {
        builder.setName(name);
        builder.setHeroClass(HeroClass.ELF);
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(100);
        builder.setDefense(10);
        builder.setHitPoints(5);
        builder.setArtefact(Artefact.HELM);
    }

    public void constructHobbit(Builder builder, String name) {
        builder.setName(name);
        builder.setHeroClass(HeroClass.ELF);
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(100);
        builder.setDefense(10);
        builder.setHitPoints(5);
        builder.setArtefact(Artefact.ARMOR);
    }
}
