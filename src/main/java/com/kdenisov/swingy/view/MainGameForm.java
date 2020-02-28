package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.Hero;

public class MainGameForm {
    public void Start(Hero hero) {
        System.out.println(hero.getName() + " " + hero.getHeroClass());
    }
}
