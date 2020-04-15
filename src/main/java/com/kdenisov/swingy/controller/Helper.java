package com.kdenisov.swingy.controller;

import com.kdenisov.swingy.model.*;

public class Helper {
    private int attack;
    private int defense;

    public int getAttack(HeroClass heroClass) {
        switch (heroClass) {
            case Elf:
                attack = 105;
                break;
            case Dwarf:
                attack = 110;
                break;
            case Wizard:
                attack = 100;
                break;
        }

        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense(HeroClass heroClass) {
        switch (heroClass) {
            case Elf:
                defense = 50;
                break;
            case Dwarf:
                defense = 60;
                break;
            case Wizard:
                defense = 65;
                break;
        }

        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

}
