package de.tu.dresden.ifsr.kurs.java.roguelike;

import de.tu.dresden.ifsr.kurs.java.roguelike.view.GameWindow;

public class Main {

    public static void main(String[] args) {
        try {
            GameWindow game = GameWindow.getInstance();
            game.setText("The awesome rogue-like-game");

            while (game.isActive()) {
            }

            System.out.println("game closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
