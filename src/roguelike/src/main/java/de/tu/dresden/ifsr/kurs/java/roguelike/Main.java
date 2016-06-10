package de.tu.dresden.ifsr.kurs.java.roguelike;

import de.tu.dresden.ifsr.kurs.java.roguelike.controller.GameController;
import de.tu.dresden.ifsr.kurs.java.roguelike.excetions.CharacterException;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Enemy;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Player;
import de.tu.dresden.ifsr.kurs.java.roguelike.view.GameWindow;

public class Main {

    public static void main(String[] args) {
        GameController game = null;

        try {
            game = new GameController(GameWindow.DIM_X, GameWindow.DIM_Y);
            if (initCharacters(game))
                game.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            if (game != null)
                game.close();
        }

        System.out.println("game closed");
    }

    private static boolean initCharacters(GameController game) {
        System.out.println("Start character-creation.");

        try {
            game.addPlayer(new Player("Siegfried", Gender.MAN));

            game.addObjectToWorld(new Enemy("Wölfchen", Gender.MAN));
            game.addObjectToWorld(new Enemy("Spinnchen", Gender.WOMAN));

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
