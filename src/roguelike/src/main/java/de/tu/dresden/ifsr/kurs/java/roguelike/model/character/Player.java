package de.tu.dresden.ifsr.kurs.java.roguelike.model.character;

import de.tu.dresden.ifsr.kurs.java.roguelike.controller.InputController;
import de.tu.dresden.ifsr.kurs.java.roguelike.exceptions.CharacterException;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.Direction;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;
import javafx.scene.input.KeyCode;

public class Player extends Character {

    private static final String charcter = "\u263A";

    private int armor;
    private boolean hasMoved;

    public Player(String name, Gender gender) throws CharacterException {
        super(name, gender);

        armor = 5;
        hasMoved = false;
    }

    public boolean collect() {
        return false;
    }

    @Override
    public Direction move() {
        Direction result = Direction.NONE;

        if (InputController.INSTANCE.keyWasPressed(KeyCode.W)) {
            result = Direction.UP;
        }

        if (InputController.INSTANCE.keyWasPressed(KeyCode.S)) {
            result = Direction.DOWN;
        }

        if (InputController.INSTANCE.keyWasPressed(KeyCode.A)) {
            result = Direction.LEFT;
        }

        if (InputController.INSTANCE.keyWasPressed(KeyCode.D)) {
            result = Direction.RIGHT;
        }

        InputController.INSTANCE.resetPressedKeys();

        return result;
    }

    @Override
    public void fightAgain(Character fighter) throws CharacterException {
        if (fighter == null)
            throw new CharacterException("There is no charecter to fight again.");

        if (fighter instanceof Player)
            throw new CharacterException("You can't fight again an other player.");

        System.out.println("Ich kämpfe gegen einen Feind.");
    }

    @Override
    public String getVisibleCharacter() {
        return charcter;
    }
}
