package de.tu.dresden.ifsr.kurs.java.roguelike.model;

import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Character;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Enemy;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Player;

public class ModelStarter {

    public static void modelTest() {

        Character hero = new Player("Uwe", Gender.MAN);
        Character wolf = new Enemy("Wulfie", Gender.WOMAN);

        hero.fightAgain(wolf);
        wolf.fightAgain(hero);

        /*
        String ausgabe = "The hero '" + hero.getName() +
                "' has: " + hero.getHp() + " HP.";

        System.out.println(ausgabe);
        */
    }
}
