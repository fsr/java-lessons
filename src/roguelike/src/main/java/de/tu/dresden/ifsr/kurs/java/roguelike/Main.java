package de.tu.dresden.ifsr.kurs.java.roguelike;

import de.tu.dresden.ifsr.kurs.java.roguelike.controller.GameController;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.Gender;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Enemy;
import de.tu.dresden.ifsr.kurs.java.roguelike.model.character.Player;

public class Main {

    public static void main(String[] args) {
        GameController game = new GameController();
        game.addObjectToWorld(new Player("Siegfried", Gender.MAN));
        game.addObjectToWorld(new Enemy("Wölfchen", Gender.MAN));

        game.run();

        System.out.println("game closed");
    }
}
