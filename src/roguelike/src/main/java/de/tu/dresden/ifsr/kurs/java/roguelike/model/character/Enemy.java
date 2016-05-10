package de.tu.dresden.ifsr.kurs.java.roguelike.model.character;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;

public class Enemy extends Character {

    public Enemy(String name, Gender gender) {
        super(name, gender);
    }

    @Override
    public void fightAgain(Character fighter) {
        System.out.println("Ich kämpfe gegen einen Spieler.");
    }
}
