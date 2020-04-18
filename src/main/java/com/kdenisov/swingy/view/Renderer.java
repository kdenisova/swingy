package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.GameEngine;
import com.kdenisov.swingy.controller.HibernateManager;
import com.kdenisov.swingy.model.Villain;

import java.io.IOException;

public interface Renderer {
    void renderMenu();
    void renderPlayground(GameEngine gameEngine, int mapSize);
    void updateGameAction(String str);
    void updateAttack(int attack);
    void updateDefense(int defense);
    void updateHitPoints(int hitPoints);
    void updateExperience(int experience);
    void updateArtifacts();
    int chooseAction(Villain villain);
    void showMessageDialog(int flag, int val);
    void renderHero(int oldY, int oldX, int newY, int newX);
    void renderVillians();
    void renderObstacle();
    void removeVillain(int y, int x);
}
