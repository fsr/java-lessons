package de.tu.dresden.ifsr.kurs.java.roguelike.model.structures;

import de.tu.dresden.ifsr.kurs.java.roguelike.excetions.InvalidPointException;

public class Point {

    private int x;
    private int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE)
            throw new InvalidPointException(
                    "A position is only possible, if x solves the condition Min.Int < x < Max.Int.");

        this.x = x;
    }

    public void setY(int y) {
        if (y == Integer.MAX_VALUE || y == Integer.MIN_VALUE)
            throw new InvalidPointException(
                    "A position is only possible, if y solves the condition Min.Int < y < Max.Int.");

        this.y = y;
    }
}
