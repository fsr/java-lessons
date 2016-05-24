package de.tu.dresden.ifsr.kurs.java.roguelike.model.character;

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

    public Character(String name, Gender gender) {
        if (name == null || name.isEmpty())
            name = "Nobody";

        this.name = name;
        this.gender = gender;
        this.position = new Point();

        hp = 100;
        strength = 10;
        intelligence = 10;
    }

    public abstract void fightAgain(Character fighter);

    public void move() {

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
        this.position = position;
    }
}
