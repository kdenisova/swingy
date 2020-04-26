package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.GameEngine;
import com.kdenisov.swingy.model.Villain;

import java.util.List;

public interface Renderer {
    void renderMenu();
    void renderPlayground(GameEngine gameEngine, int mapSize, List<String> gameAction);
    void updateGameAction(String str);
    void updateAttack(int attack);
    void updateDefense(int defense);
    void updateHitPoints(int hitPoints);
    void updateExperience(int experience);
    void updateArtifacts();
    int chooseAction(Villain villain);
    void showMessageDialog(int flag, int val);
    void renderHero(int oldY, int oldX, int newY, int newX);
    void renderVillains();
    void renderObstacle();
    void removeVillain(int y, int x);
    void saveGame();
}
