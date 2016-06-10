package de.tu.dresden.ifsr.kurs.java.roguelike.controller;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.Direction;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.VisibleObject;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Player;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.Point;
import de.tu.dresden.ifsr.kurs.java.roguelike.view.GameWindow;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameController {

    private Map<Point, List<VisibleObject>> worldObjects;
    private Player player;

    public GameController(int width, int height) {
        worldObjects = initWorld(width, height);
    }

    private Map<Point, List<VisibleObject>> initWorld(int width, int height) {
        Map<Point, List<VisibleObject>> result = new TreeMap<Point, List<VisibleObject>>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.put(new Point(x, y), new ArrayList<VisibleObject>());
            }
        }

        return result;
    }

    public boolean addPlayer(Player player) {
        if (this.player != null)
            return false;

        this.player = player;
        return addObjectToWorld(player);
    }

    public boolean addObjectToWorld(VisibleObject obj) {
        if (!worldObjects.containsKey(obj.getPosition()))
            return false;

        return worldObjects.get(obj.getPosition()).add(obj);
    }

    public void run() {
        if (player == null)
            throw new InvalidStateException("There must be one player.");

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

    public void close() {
        GameWindow.getInstance().stop();
    }

    public void removeAllWorldObjects() {
        for (List<VisibleObject> field : worldObjects.values()) {
            field.clear();
        }
    }

    private void render(GameWindow gameWindow) {
        StringBuilder gameBoard = new StringBuilder();

        for (Point field : worldObjects.keySet()) {
            List<VisibleObject> objects = worldObjects.get(field);

            if (objects == null || objects.isEmpty()) {
                gameBoard.append("\u00A0");
            } else {
                if (objects.contains(player)) {
                    gameBoard.append(player.getVisibleCharacter());
                } else {
                    gameBoard.append(objects.get(0).getVisibleCharacter());
                }
            }
        }

        gameWindow.setText(gameBoard.toString());
    }

    private void process() {
        if (InputController.INSTANCE.keyWasPressed()) {
            Point currentPosition = new Point();
            List<VisibleObject> objectsToMove = new ArrayList<VisibleObject>();

            for (Point field : worldObjects.keySet()) {
                for (VisibleObject obj : worldObjects.get(field)) {
                    Direction direction = obj.move();
                    currentPosition.setPosition(field);

                    switch (direction) {
                        case DOWN:
                            currentPosition.setY(currentPosition.getY() + 1);
                            break;
                        case UP:
                            currentPosition.setY(currentPosition.getY() - 1);
                            break;
                        case RIGHT:
                            currentPosition.setX(currentPosition.getX() + 1);
                            break;
                        case LEFT:
                            currentPosition.setX(currentPosition.getX() - 1);
                            break;
                        case NONE:
                            break;
                        default:
                            break;
                    }

                    if (worldObjects.containsKey(currentPosition)) {
                        obj.setPosition(currentPosition);
                        objectsToMove.add(obj);
                    }
                }

                if (!objectsToMove.isEmpty()) {
                    List<VisibleObject> currentObjects = worldObjects.get(field);

                    for (VisibleObject obj : objectsToMove) {
                        worldObjects.get(obj.getPosition()).add(obj);
                        currentObjects.remove(obj);
                    }

                    objectsToMove.clear();
                }
            }
        }
    }
}
