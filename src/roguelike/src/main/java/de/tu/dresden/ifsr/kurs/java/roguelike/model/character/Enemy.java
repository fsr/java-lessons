package de.tu.dresden.ifsr.kurs.java.roguelike.model.character;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;

public class Enemy extends Character {

    private static final String charcter = "M";

    public Enemy(String name, Gender gender) {
        super(name, gender);

        position.setX(10);
        position.setY(7);
    }

    @Override
    public void fightAgain(Character fighter) {
        System.out.println("Ich kämpfe gegen einen Spieler.");
    }

    @Override
    public String getVisibleCharacter() {
        return charcter;
    }
}
