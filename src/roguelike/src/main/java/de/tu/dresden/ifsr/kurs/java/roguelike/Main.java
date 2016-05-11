package de.tu.dresden.ifsr.kurs.java.roguelike;

import de.tu.dresden.ifsr.kurs.java.roguelike.view.GameWindow;

public class Main {

    public static void main(String[] args) {
        try {
            GameWindow game = GameWindow.getInstance();
            while (game.isActive()) Thread.sleep(100);

            System.out.println("game closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
