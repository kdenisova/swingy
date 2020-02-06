package com.kdenisov.swingy.model;

public interface Builder {
    void setName(String name);
    void setHeroClass(HeroClass heroClass);
    void setLevel(int level);
    void setExperience(int experience);
    void setAttack(int attack);
    void setDefense(int defense);
    void setHitPoints(int hitPoints);
    void setArtefact(Artefact artefact);
}
