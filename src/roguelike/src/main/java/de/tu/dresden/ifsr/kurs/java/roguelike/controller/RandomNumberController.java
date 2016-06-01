package de.tu.dresden.ifsr.kurs.java.roguelike.controller;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.Point;
import de.tu.dresden.ifsr.kurs.java.roguelike.view.GameWindow;

import java.util.Random;

public enum RandomNumberController {
    INSTANCE;

    private final Random randomizer;

    RandomNumberController() {
        randomizer = new Random(System.currentTimeMillis());
    }

    public static RandomNumberController getInstance() {
        return INSTANCE;
    }

    public Point getWorldPoint() {
        return new Point(randomizer.nextInt(GameWindow.DIM_X), randomizer.nextInt(GameWindow.DIM_Y));
    }
}
