package de.tu.dresden.ifsr.kurs.java.roguelike.model.structures;

import de.tu.dresden.ifsr.kurs.java.roguelike.exceptions.InvalidPointException;

// Schauen warum maven tests nicht ausführt


/**
 * Representing a point in 2D.
 *
 * @author uwe schmidt
 * @version 1.0.0
 */
public class Point implements Comparable<Point> {

    /**
     * Prime-base for transform a point into 1D.
     */
    public static final int PRIME_BASE = 101;

    /**
     * X-Axis-value for point.
     */
    private int x;

    /**
     * Y-Axis-value for point.
     */
    private int y;

    /**
     * Initialize a Point-object.
     * Default-coordinates are (0,0).
     */
    public Point() {
        setPosition(0, 0);
    }

    /**
     * Initialize a Point-object.
     *
     * @param x X-Axis-value
     * @param y Y-Axis-value
     */
    public Point(int x, int y) {
        setPosition(x, y);
    }

    /**
     * Initialize a Point-object.
     *
     * @param point Take initial coordinates from given Point.
     * @see de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.Point
     */
    public Point(Point point) {
        setPosition(point);
    }

    /**
     * Gives the X-Axis-value.
     *
     * @return X-Axis-Value from point.
     */
    public int getX() {
        return x;
    }

    /**
     * Gives the Y-Axis-value.
     *
     * @return Y-Axis-value from point.
     */
    public int getY() {
        return y;
    }

    /**
     * Set X-Axis-value for point.
     *
     * @param x X-Axis-value
     * @throws InvalidPointException if X-Axis-value
     *                               is equal Integer.MIN_VALUE or
     *                               Integer.MAX_VALUE
     */
    public void setX(int x) {
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE)
            throw new InvalidPointException(
                    "A position is only possible, if x solves the condition Min.Int < x < Max.Int.");

        this.x = x;
    }

    /**
     * Set Y-Axis-value for point.
     *
     * @param y Y-Axis-value
     * @throws InvalidPointException if Y-Axis-value
     *                               is equal Integer.MIN_VALUE or
     *                               Integer.MAX_VALUE
     */
    public void setY(int y) {
        if (y == Integer.MAX_VALUE || y == Integer.MIN_VALUE)
            throw new InvalidPointException(
                    "A position is only possible, if y solves the condition Min.Int < y < Max.Int.");

        this.y = y;
    }

    /**
     * Sets the position to given coordinates.
     *
     * @param x X-Axis-value
     * @param y Y-Axis-value
     */
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Sets the position to the given coordinates from an other Point.
     * <strong>Take initial coordinates from given Point.</strong>
     *
     * @see de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.Point
     */
    public void setPosition(Point point) {
        setX(point.x);
        setY(point.y);
    }

    /**
     * Compares this point with an other Point.
     * If the given Point is lower the value is -1.
     * If the given Point is equal the value is 0.
     * If the given Point is greater the value is 1.
     * Use the hashCode-method for calculation.
     *
     * @param point Point to compare with.
     * @return Solution of comparison.
     * @see de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.Point
     */
    @Override
    public int compareTo(Point point) {
        return hashCode() - point.hashCode();
    }

    /**
     * Gives the has-code from this point.
     * The value is the result from the 2D to 1D transformation
     * with the general-prime-base.
     *
     * @return hash-value from this point.
     */
    @Override
    public int hashCode() {
        return (PRIME_BASE + y) * PRIME_BASE + x;
    }

    /**
     * Compare two Points.
     *
     * @param o other Point for comparison.
     * @return 'true' if the given object is a Point and has the
     * same hashCode.
     * @see de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.Point
     * @see java.lang.Object
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;

        return hashCode() == o.hashCode();
    }
}
