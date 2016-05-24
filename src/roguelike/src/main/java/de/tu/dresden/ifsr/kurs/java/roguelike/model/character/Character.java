package de.tu.dresden.ifsr.kurs.java.roguelike.model.character;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;

public abstract class Character {

    private String name;
    private Gender gender;
    private int hp;
    private int strength;
    private int intelligence;

    public Character(String name, Gender gender) {
        if(name == null || name.isEmpty())
            name = "Nobody";

        this.name = name;
        this.gender = gender;

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
}
