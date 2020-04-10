package com.kdenisov.swingy.model;

import java.util.Random;

public enum ObstacleType {
    Tree,
    Rock,
    Reed,
    Fire,
    Flower,
    Sunflower;

    public static ObstacleType getRandomObstacle() {
        Random random = new Random();

        return values()[random.nextInt(values().length)];
    }
}
