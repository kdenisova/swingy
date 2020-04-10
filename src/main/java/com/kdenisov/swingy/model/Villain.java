package com.kdenisov.swingy.model;

import javax.persistence.*;
import java.util.ArrayList;

public class Villain extends GameEntity {
//    private int id;
//    private String name;
//    private int level;
    private int attack;
//    private int defense;
//    private int hitPoints;
//    private int y;
//    private int x;
    private VillainType villainType;

    public Villain(VillainType villainType, int attack, int y, int x) {
        super(EntityType.Villain, y, x);
        this.attack = attack;
        this.villainType = villainType;
    }

    public VillainType getVillainType() {
        return villainType;
    }

    public void setVillainType(VillainType villainType) {
        this.villainType = villainType;
    }

//    public Villain(int id, String name, int level,
//                int attack, int defense, int hitPoints, int y, int x) {
//        super(EntityType.Villain, y, x);
//
//        this.id = id;
//        this.name = name;
//        this.level = level;
//        this.attack = attack;
//        this.defense = defense;
//        this.hitPoints = hitPoints;
//        //this.y = y;
//        //this.x = x;
//
//    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getLevel() {
//        return level;
//    }
//
//    public void setLevel(int level) {
//        this.level = level;
//    }
//
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
//
//    public int getDefense() {
//        return defense;
//    }
//
//    public void setDefense(int defense) {
//        this.defense = defense;
//    }
//
//    public int getHitPoints() {
//        return hitPoints;
//    }
//
//    public void setHitPoints(int hitPoints) {
//        this.hitPoints = hitPoints;
//    }

//    public int getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
}
