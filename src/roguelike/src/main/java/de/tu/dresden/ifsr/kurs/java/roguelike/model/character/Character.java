package de.tu.dresden.ifsr.kurs.java.roguelike.model.character;

import de.tu.dresden.ifsr.kurs.java.roguelike.controller.RandomNumberController;
import de.tu.dresden.ifsr.kurs.java.roguelike.exceptions.CharacterException;
import de.tu.dresden.ifsr.kurs.java.roguelike.exceptions.InvalidPointException;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.Direction;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.VisibleObject;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.Point;

public abstract class Character implements VisibleObject {

    private String name;
    private Gender gender;
    private int hp;
    private int strength;
    private int intelligence;

    protected Point position;

    /**
     * Creates a character.
     *
     * @param name   Name of the character. (NOT null and NOT empty)
     * @param gender Gender of the character
     * @throws CharacterException
     */
    public Character(String name, Gender gender) throws CharacterException {
        if (name == null || name.isEmpty())
            throw new CharacterException("A character need a name.");

        this.name = name;
        this.gender = gender;
        this.position = RandomNumberController.INSTANCE.getWorldPoint();

        hp = 100;
        strength = 10;
        intelligence = 10;
    }

    public abstract void fightAgain(Character fighter) throws CharacterException;

    @Override
    public Direction move() {
        return RandomNumberController.INSTANCE.getDirection();
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        if (position == null)
            throw new InvalidPointException("The position to set this charcter is invalid.");

        this.position.setPosition(position);
    }
}
