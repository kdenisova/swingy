package com.kdenisov.swingy.controller;

import com.kdenisov.swingy.model.Hero;
import com.kdenisov.swingy.model.HibernateManager;
import com.kdenisov.swingy.model.Villain;
import com.kdenisov.swingy.model.VillainEntity;
import com.kdenisov.swingy.view.Playground;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Hero hero;
    private List<Villain> villains;
    private HibernateManager hibernateManager;
    private int mapSize;
    private boolean status;
    private Playground gameForm;

    public GameEngine(HibernateManager hibernateManager, Hero hero) {
        this.hero = hero;
        this.hibernateManager = hibernateManager;
    }

    public void play() {
        setStatus(true);
        setMapSize();
        setVillains();

        gameForm = new Playground(hibernateManager, mapSize, this);
        gameForm.render();
    }

    public int randomGenerator() {
        return (int) (Math.random() * (mapSize));
    }

    public void setVillains() {
        List<VillainEntity> villainEntities = hibernateManager.getListVillains();
        this.villains = new ArrayList<>();

        int x, y, id;

        for (int i = 0; i < mapSize; i++) {
            id = randomGenerator();
            x = randomGenerator();
            y = randomGenerator();

            while ((x == hero.getX() && y == hero.getY()) || isOccupied(x, y)) {
                x = randomGenerator();
                y = randomGenerator();
            }

            Villain villain = new Villain(villainEntities.get(id).getId(), villainEntities.get(id).getName(), villainEntities.get(id).getLevel(),
                    villainEntities.get(id).getAttack(), villainEntities.get(id).getDefense(), villainEntities.get(id).getHitPoints(),
                    x, y);
            this.villains.add(villain);
        }
    }

    public List<Villain> getVillains() {
        return villains;
    }

    public boolean isOccupied(int x, int y) {
        //System.out.println("size = " + villains.size());
        for (Villain villian : villains) {
            //System.out.println(String.format("The hero is in the [%d,%d] and the %s in in the [%d,%d]", x, y, villian.getName(), villian.getX(), villian.getY() ));
            if (villian.getX() == x && villian.getY() == y) {
                //System.out.println("Oops");
                return true;
            }
            //System.out.println("Missed! Ha-ha");
        }
        return false;
    }

    public void heroMoved(HeroMove move) {
        int x, y;
        x = hero.getX();
        y = hero.getY();
        switch (move) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        if (isOccupied(x, y)) {
            int result = gameForm.chooseAction(y, x);
            System.out.println(result);
            //gameForm.showMessageDialog();
            return;
        }

        int oldX = hero.getX();
        int oldY = hero.getY();

        hero.setX(x);
        hero.setY(y);

        gameForm.renderHero(oldX, oldY, x, y);
    }

    public Hero getHero() {
        return hero;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize() {
        this.mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
