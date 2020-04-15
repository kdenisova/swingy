package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.GameEngine;
import com.kdenisov.swingy.controller.HibernateManager;

public class CPlayground {
    private HibernateManager hibernateManager;
    private GameEngine game;
    private int mapSize;

    public CPlayground(HibernateManager hibernateManager, int mapSize, GameEngine game) {
        this.hibernateManager = hibernateManager;
        this.mapSize = mapSize;
        this.game = game;
    }

    public void render() {
        System.out.print("\033\143");
        System.out.println("There will be map");
    }
}
