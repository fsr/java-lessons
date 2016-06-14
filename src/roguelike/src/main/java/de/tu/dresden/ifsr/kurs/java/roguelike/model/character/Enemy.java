package de.tu.dresden.ifsr.kurs.java.roguelike.model.character;

import de.tu.dresden.ifsr.kurs.java.roguelike.exceptions.CharacterException;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;

public class Enemy extends Character {

    private static final String charcter = "M";

    public Enemy(String name, Gender gender) throws CharacterException {
        super(name, gender);
    }

    @Override
    public void fightAgain(Character fighter) throws CharacterException {
        if (fighter == null)
            throw new CharacterException("There is no charecter to fight again.");

        System.out.println("Ich kämpfe gegen einen Spieler.");
    }

    @Override
    public String getVisibleCharacter() {
        return charcter;
    }
}
