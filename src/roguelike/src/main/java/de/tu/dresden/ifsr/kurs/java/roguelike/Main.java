package de.tu.dresden.ifsr.kurs.java.roguelike;

import de.tu.dresden.ifsr.kurs.java.roguelike.controller.GameController;
import de.tu.dresden.ifsr.kurs.java.roguelike.excetions.CharacterException;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Enemy;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Player;

public class Main {

    public static void main(String[] args) {
        try {
            GameController game = new GameController();

            if (initCharacters(game))
                game.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("game closed");
    }

    private static boolean initCharacters(GameController game) {
        System.out.println("Start character-creation.");

        try {
            game.addObjectToWorld(new Player("Siegfried", Gender.MAN));
            game.addObjectToWorld(new Enemy("Wölfchen", Gender.MAN));

            return true;
        } catch (CharacterException ex) {
            System.out.println(ex.getMessage());

            game.removeAllWorldObjects();
            System.out.println("Inkonsistent state. No character was created.");

            return false;
        } finally {
            System.out.println("Finished character-creation.");
        }
    }
}
