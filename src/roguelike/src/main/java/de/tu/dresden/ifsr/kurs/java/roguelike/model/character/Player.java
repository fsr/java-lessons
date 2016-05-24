package de.tu.dresden.ifsr.kurs.java.roguelike.model.character;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;

public class Player extends Character {

    private int armor;

    public Player(String name, Gender gender) {
        super(name, gender);
        armor = 5;
    }

    public boolean collect() {
        return false;
    }

    @Override
    public void fightAgain(Character fighter)
    {
        System.out.println("Ich kämpfe gegen einen Feind.");
    }
}
