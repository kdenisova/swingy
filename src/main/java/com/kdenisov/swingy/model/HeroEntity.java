package com.kdenisov.swingy.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "HERO", schema = "swingy")
public class HeroEntity {

    private int id;
    private String name;
    private HeroClass heroClass;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;
    private ArrayList<Artifact> artifacts;
    private int y;
    private int x;


    public HeroEntity(String name, HeroClass heroClass, int level, int experience, int attack, int defense, int hitPoints, ArrayList<Artifact> artifacts) {

        this.name = name;
        this.heroClass = heroClass;
        this.level = level;
        this.experience = experience;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.artifacts = artifacts;
    }

    public HeroEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "int default 1")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Level")
    public int getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "Experience")
    public int getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "Attack")
    public int getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    @Basic
    @Column(name = "Defense")
    public int getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    @Basic
    @Column(name = "HitPoints")
    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Basic
    @Column(name = "Y")
    public int getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Basic
    @Column(name = "X")
    public int getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    @Basic
    @Column(name = "Class")
    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroEntity hero = (HeroEntity) o;
        return level == hero.level &&
                experience == hero.experience &&
                attack == hero.attack &&
                defense == hero.defense &&
                hitPoints == hero.hitPoints &&
                id == hero.id &&
                Objects.equals(name, hero.name) &&
                Objects.equals(heroClass, hero.heroClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, heroClass, level, experience, attack, defense, hitPoints);
    }
}
