package com.kdenisov.swingy.model;

import javax.persistence.*;
import java.util.ArrayList;

public class Villain {
    private int id;
    private String name;
    private int level;
    private int attack;
    private int defense;
    private int hitPoints;
    private int x;
    private int y;

    public Villain(int id, String name, int level,
                int attack, int defense, int hitPoints, int x, int y) {

        this.id = id;
        this.name = name;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
