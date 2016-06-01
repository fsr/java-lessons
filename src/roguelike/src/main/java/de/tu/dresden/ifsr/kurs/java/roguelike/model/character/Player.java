package de.tu.dresden.ifsr.kurs.java.roguelike.model.character;

import de.tu.dresden.ifsr.kurs.java.roguelike.controller.InputController;
import de.tu.dresden.ifsr.kurs.java.roguelike.excetions.CharacterException;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;
import javafx.scene.input.KeyCode;

public class Player extends Character {

    private static final String charcter = "\u263A";

    private int armor;

    public Player(String name, Gender gender) throws CharacterException {
        super(name, gender);

        armor = 5;
    }

    public boolean collect() {
        return false;
    }

    @Override
    public void move() {
        if (InputController.INSTANCE.keyWasPressed(KeyCode.W)) {
            position.setY(position.getY() - 1);
            return;
        }

        if (InputController.INSTANCE.keyWasPressed(KeyCode.S)) {
            position.setY(position.getY() + 1);
            return;
        }

        if (InputController.INSTANCE.keyWasPressed(KeyCode.A)) {
            position.setX(position.getX() - 1);
            return;
        }

        if (InputController.INSTANCE.keyWasPressed(KeyCode.D)) {
            position.setX(position.getX() + 1);
            return;
        }
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
