package de.tu.dresden.ifsr.kurs.java.roguelike.model.structures;

import de.tu.dresden.ifsr.kurs.java.roguelike.exceptions.InvalidPointException;

public class Point implements Comparable<Point> {

    public static final int PRIME_BASE = 101;

    private int x;
    private int y;

    public Point() {
        setPosition(0, 0);
    }

    public Point(int x, int y) {
        setPosition(x, y);
    }

    public Point(Point point) {
        setPosition(point);
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

    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setPosition(Point point) {
        x = point.x;
        y = point.y;
    }

    @Override
    public int compareTo(Point point) {
        return hashCode() - point.hashCode();
    }

    @Override
    public int hashCode() {
        return (PRIME_BASE + y) * PRIME_BASE + x;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;

        return hashCode() == o.hashCode();
    }
}
