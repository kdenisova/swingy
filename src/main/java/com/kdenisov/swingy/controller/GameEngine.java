package com.kdenisov.swingy.controller;

import com.kdenisov.swingy.model.*;
import com.kdenisov.swingy.view.Playground;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Hero hero;
    private List<Villain> villains;
    private List<Obstacle> obstacles;
    private List<GameEntity> entities;
    private HibernateManager hibernateManager;
    private int mapSize;
    private boolean status;
    private Playground playground;

    public GameEngine(HibernateManager hibernateManager, Hero hero) {
        this.hero = hero;
        this.hibernateManager = hibernateManager;
    }

    public void play() {
        entities = new ArrayList<>();
        setStatus(true);
        setMapSize();
        setVillains();
        setObstacles();
        playground = new Playground(hibernateManager, mapSize, this);
        playground.render();
    }

    public void continueGame() {
        entities = new ArrayList<>();
        setStatus(true);
        setMapSize();

        try {
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/saved/" +
                    hero.getId() + ".ser");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            entities = (List<GameEntity>) objectInputStream.readObject();
            villains = (List<Villain>) objectInputStream.readObject();
            obstacles = (List<Obstacle>) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("Loaded");
        } catch (Exception e) {
            e.printStackTrace();
        }

        playground = new Playground(hibernateManager, mapSize, this);
        playground.render();
    }

    public void clear() {
        setMapSize();
        hero.setY(mapSize / 2);
        hero.setX(mapSize / 2);
        hibernateManager.updateHero(hero);
        villains.clear();

        play();
    }

    public int randomGenerator(int n) {
        return (int) (Math.random() * (n));
    }

    public void setVillains() {
        //List<VillainEntity> villainEntities = hibernateManager.getListVillains();
        this.villains = new ArrayList<>();

        int y, x, id;
        int attackRange = hero.getAttack() - 30;
        for (int i = 0; i < mapSize * 2; i++) {
            //id = randomGenerator(villainEntities.size());
            y = randomGenerator(mapSize);
            x = randomGenerator(mapSize);

            while ((y == hero.getY() && x == hero.getX()) || isOccupied(y, x)) {
                y = randomGenerator(mapSize);
                x = randomGenerator(mapSize);
            }

            Villain villain = new Villain(VillainType.getRandomVillain(), randomGenerator(attackRange) + 40, y, x);
//            Villain villain = new Villain(villainEntities.get(id).getId(), villainEntities.get(id).getName(),
//                    villainEntities.get(id).getLevel(), villainEntities.get(id).getAttack(),
//                    villainEntities.get(id).getDefense(), villainEntities.get(id).getHitPoints(), y, x);
            this.villains.add(villain);
            entities.add(villain);
        }
    }

    public List<Villain> getVillains() {
        return villains;
    }

    public void setObstacles() {
        this.obstacles = new ArrayList<>();

        int y, x;

        for (int i = 0; i < mapSize; i++) {
            y = randomGenerator(mapSize);
            x = randomGenerator(mapSize);

            while ((y == hero.getY() && x == hero.getX()) || isOccupied(y, x)) {
                y = randomGenerator(mapSize);
                x = randomGenerator(mapSize);
            }

            Obstacle obstacle = new Obstacle(ObstacleType.getRandomObstacle(), y, x);
            this.obstacles.add(obstacle);
            entities.add(obstacle);
        }
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<GameEntity> getGameEntities() {
        return entities;
    }

    public boolean isOccupied(int y, int x) {
        for (GameEntity entity : entities) {
            if (y == entity.getY() && x == entity.getX()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEntity(int y, int x) {
        for (GameEntity entity : entities) {
            if (entity.getY() == y && entity.getX() == x) {
                switch (entity.getEntityType()) {
                    case Villain:
                        return interact((Villain)entity);
                    case Obstacle:
                        //interact((Sanitizer)entity);
                        return false;
                }
            }
        }
        return true;
    }

    public void findArtifact() {
        int n = 0;
        Artifact artifact = null;

        while (n == 0) {
            n = randomGenerator(3);

            switch (n) {
                case 1:
                    artifact = Artifact.Helm;
                    break;
                case 2:
                    artifact = Artifact.Weapon;
                    break;
                default:
                    artifact = Artifact.Armor;
                    break;
            }

            if (hero.getArtifacts().contains(artifact))
                n = 0;
            else
                break;
        }

        hero.getArtifacts().add(artifact);
        hibernateManager.updateArtifacts(hero, artifact);
        playground.updateArtifacts();

        String msg = null;
        int points = 10 * hero.getLevel();

        switch (artifact) {
            case Armor:
                hero.setDefense(hero.getDefense() + points);
                playground.updateDefense(hero.getDefense());
                msg = "Defense";
                break;
            case Helm:
                hero.setHitPoints(hero.getHitPoints() + points);
                playground.updateHitPoints(hero.getHitPoints());
                msg = "Hit Points";
                break;
            case Weapon:
                hero.setAttack(hero.getAttack() + points);
                playground.updateAttack(hero.getAttack());
                msg = "Attack";
                break;
        }

        playground.updateGameAction("Found " + artifact + ". + " + points + " to " + msg);
    }

    public void removeEntity(GameEntity entity) {
        playground.removeVillain(entity.getY(), entity.getX());
        entities.remove(entity);
        villains.remove(entity);
    }

    public boolean interact(Villain villain) {
        int result = playground.chooseAction(villain);
        if (result == 1) {
            if (!fight(villain))
                return false;
        }
        else {
            if (randomGenerator(2) == 1) {
                playground.updateGameAction("Couldn't run from the villain");
                playground.showMessageDialog(3, 0);
                if (!fight(villain))
                    return false;
            }
            else {
                playground.updateGameAction("Escaped from the villain");
                return false;
            }
        }
        return true;
    }

    public boolean fight(Villain villain) {
        boolean result = true;

        int hitPoints, experience;
        if (hero.getAttack() < villain.getAttack()) {
            hitPoints = (hero.getHitPoints() + hero.getDefense()) - villain.getAttack();

            if (hitPoints > 0) {
                hero.setHitPoints(hitPoints);
                playground.updateHitPoints(hitPoints);
                playground.showMessageDialog(5, villain.getAttack() - hero.getDefense());
                playground.updateGameAction(villain.getVillainType() + " does " + (villain.getAttack() - hero.getDefense()) + " damage");
                removeEntity(villain);
            }
            else {
                hero.setHitPoints(0);
                playground.updateHitPoints(0);
                playground.showMessageDialog(4, hero.getExperience());
                playground.updateGameAction("Too much damage from " + villain.getVillainType());
                result = false;
                status = false;
            }
        } else if (hero.getAttack() == villain.getAttack()) {
            hitPoints = ((hero.getHitPoints() + hero.getDefense()) - villain.getAttack()) / 2;

            if (hitPoints > 0) {
                experience = (int) (villain.getAttack() * 1.7);
                hero.setExperience(hero.getExperience() + experience);
                hero.setHitPoints(hitPoints);
                playground.showMessageDialog(2, experience);
                playground.updateExperience(hero.getExperience());
                playground.updateHitPoints(hitPoints);
                playground.updateGameAction(villain.getVillainType() + " does " + hitPoints + " damage");
                playground.updateGameAction("Earned " + experience + " experience after fight with " + villain.getVillainType());
                removeEntity(villain);
            }
            else {
                hero.setHitPoints(0);
                playground.updateHitPoints(0);
                playground.showMessageDialog(4, hero.getExperience());
                playground.updateGameAction("Too much damage from " + villain.getVillainType());
                result = false;
                status = false;
            }
        }
        else {
            experience = (int) (villain.getAttack() * 1.7);
            playground.showMessageDialog(2, experience);
            hero.setExperience(hero.getExperience() + experience);
            playground.updateExperience(hero.getExperience());
            removeEntity(villain);
            playground.updateGameAction("Earned " + experience + " experience after fight with " + villain.getVillainType());
        }

        if (status && hero.getArtifacts().size() < 3) {
            if (randomGenerator(100) % 7 == 0)
                findArtifact();
        }

        return result;
    }

    public boolean checkLevel() {
        int level = hero.getLevel();

        if (hero.getExperience() >= (level * 1000 + Math.pow(level - 1, 2) * 450))
            return true;
        return false;
    }

    public void heroMoved(HeroMove move) {
        int y, x;

        y = hero.getY();
        x = hero.getX();

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

        if (isOccupied(y, x)) {
            if (!checkEntity(y, x))
                return;
        }

        if (status) {
            int oldY = hero.getY();
            int oldX = hero.getX();

            hero.setY(y);
            hero.setX(x);

            playground.renderHero(oldY, oldX, y, x);

            if (checkLevel()) {
                playground.showMessageDialog(1, 0);
                hero.setLevel(hero.getLevel() + 1);
                status = false;
                clear();
            }
        }
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


//    public boolean fight(int y, int x) {
//        boolean result = true;
//        Villain villain = null;
//
//        for (Villain v : villains) {
//            if (y == v.getY() && x == v.getX()) {
//                villain = v;
//                break;
//            }
//        }
//
//        int hitPoints, experience;
//        if (hero.getAttack() < villain.getAttack()) {
//            hitPoints = (hero.getHitPoints() + hero.getDefense()) - villain.getAttack();
//
//            if (hitPoints > 0) {
//                hero.setHitPoints(hitPoints);
//                playground.updateHitPoints(hitPoints);
//                playground.showMessageDialog(5, villain.getAttack() - hero.getDefense());
//                playground.updateGameAction(villain.getVillainType() + " does " + (villain.getAttack() - hero.getDefense()) + " damage");
////                playground.removeVillain(y, x);
////                entities.remove(villain);
////                villains.remove(villain);
//                removeEntity(villain, y, x);
//            }
//            else {
//                hero.setHitPoints(0);
//                playground.updateHitPoints(0);
//                playground.showMessageDialog(4, hero.getExperience());
//                playground.updateGameAction("Too much damage from " + villain.getVillainType());
//                result = false;
//                status = false;
//            }
//        } else if (hero.getAttack() == villain.getAttack()) {
//            hitPoints = ((hero.getHitPoints() + hero.getDefense()) - villain.getAttack()) / 2;
//
//            if (hitPoints > 0) {
//                experience = (int) (villain.getAttack() * 1.7);
//                hero.setExperience(hero.getExperience() + experience);
//                hero.setHitPoints(hitPoints);
//                playground.showMessageDialog(2, experience);
//                playground.updateExperience(hero.getExperience());
//                playground.updateHitPoints(hitPoints);
//                playground.updateGameAction(villain.getVillainType() + " does " + hitPoints + " damage");
//                playground.updateGameAction("Earned " + experience + " experience after fight with " + villain.getVillainType());
////                playground.removeVillain(y, x);
////                entities.remove(villain);
////                villains.remove(villain);
//                removeEntity(villain, y, x);
//            }
//            else {
//                hero.setHitPoints(0);
//                playground.updateHitPoints(0);
//                playground.showMessageDialog(4, hero.getExperience());
//                playground.updateGameAction("Too much damage from " + villain.getVillainType());
//                result = false;
//                status = false;
//            }
//        }
//        else {
//            experience = (int) (villain.getAttack() * 1.7);
//            playground.showMessageDialog(2, experience);
//            hero.setExperience(hero.getExperience() + experience);
//            playground.updateExperience(hero.getExperience());
////            entities.remove(villain);
////            playground.removeVillain(y, x);
////            villains.remove(villain);
//            removeEntity(villain, y, x);
//            playground.updateGameAction("Earned " + experience + " experience after fight with " + villain.getVillainType());
//        }
//
//        if (status && hero.getArtifacts().size() < 3) {
//            if (randomGenerator(100) % 7 == 0)
//                findArtifact();
//        }
//
//        return result;
//    }
}
