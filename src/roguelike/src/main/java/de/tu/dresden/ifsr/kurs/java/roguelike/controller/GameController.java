package de.tu.dresden.ifsr.kurs.java.roguelike.controller;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.VisibleObject;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Character;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Player;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.Point;
import de.tu.dresden.ifsr.kurs.java.roguelike.view.GameWindow;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private List<VisibleObject> worldObjects;

    public GameController() {
        worldObjects = new ArrayList<VisibleObject>();
    }

    public void addObjectToWorld(VisibleObject obj) {
        worldObjects.add(obj);
    }

    public void run() {
        if (!validWorldObjects())
            throw new InvalidStateException("There must be exactly one player.");

        try {
            GameWindow gameWindow = GameWindow.getInstance();

            while (gameWindow.isActive()) {
                process();
                render(gameWindow);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validWorldObjects() {
        int playerCount = 0;

        for (VisibleObject obj : worldObjects) {
            if (obj instanceof Player)
                playerCount++;
        }

        return playerCount == 1;
    }

    public void removeAllWorldObjects() {
        worldObjects.clear();
    }

    private void render(GameWindow gameWindow) {
        StringBuilder gameBoard = new StringBuilder();
        Point positionBuffer = null;

        gameBoard.append(new String(new char[gameWindow.DIM_X * gameWindow.DIM_Y]).replace("\0", "\u00A0"));

        for (VisibleObject worldObject : worldObjects) {
            positionBuffer = worldObject.getPosition();

            if (positionBuffer.getX() >= 0 && positionBuffer.getX() < gameWindow.DIM_X
                    && positionBuffer.getY() >= 0 && positionBuffer.getY() < gameWindow.DIM_Y) {

                gameBoard.insert((positionBuffer.getY() * gameWindow.DIM_X) + positionBuffer.getX(),
                        worldObject.getVisibleCharacter());
            }
        }

        gameWindow.setText(gameBoard.toString());
    }

    private void process() {
        if (InputController.INSTANCE.keyWasPressed()) {

            for (VisibleObject obj : worldObjects) {
                if (obj instanceof Character) {
                    ((Character) obj).move();
                }
            }

            InputController.INSTANCE.resetPressedKeys();
        }
    }
}
