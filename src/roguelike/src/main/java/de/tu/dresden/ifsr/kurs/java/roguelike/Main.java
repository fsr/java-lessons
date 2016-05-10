package de.tu.dresden.ifsr.kurs.java.roguelike;

import de.tu.dresden.ifsr.kurs.java.roguelike.view.GameWindow;

public class Main {

    public static void main(String[] args) {
        GameWindow game = GameWindow.getInsatnce();

        while (game.isActive());

        System.out.println("game closed");
    }
}
