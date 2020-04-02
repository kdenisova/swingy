package com.kdenisov.swingy.model;

public interface Builder {
    void setId(int id);
    void setName(String name);
    void setHeroClass(HeroClass heroClass);
    void setLevel(int level);
    void setExperience(int experience);
    void setAttack(int attack);
    void setDefense(int defense);
    void setHitPoints(int hitPoints);
    void setArtefact(Artifact artifact);
    void setImage(String heroImage);
    void setY(int y);
    void setX(int x);
}
