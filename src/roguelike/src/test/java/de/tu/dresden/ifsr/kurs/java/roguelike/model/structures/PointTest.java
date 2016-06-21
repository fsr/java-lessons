package de.tu.dresden.ifsr.kurs.java.roguelike.model.structures;


import de.tu.dresden.ifsr.kurs.java.roguelike.exceptions.InvalidPointException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PointTest {

    protected Point test;

    @Before
    public void beforeTest() {
        test = new Point();
    }

    @Test(expected = InvalidPointException.class)
    public void testSetX_MINValue() {
        test.setX(Integer.MIN_VALUE);
    }

    @Test(expected = InvalidPointException.class)
    public void testSetX_MAXValue() {
        test.setX(Integer.MAX_VALUE);
    }

    @Test
    public void testSetX_ValueIsOK() {
        final int value = 0;

        test.setX(value);

        Assert.assertEquals(value, test.getX());
    }

    @Test(expected = InvalidPointException.class)
    public void testSetY_MINValue() {
        test.setY(Integer.MIN_VALUE);
    }

    @Test(expected = InvalidPointException.class)
    public void testSetY() {
        test.setY(Integer.MAX_VALUE);
    }

    @Test
    public void testSetY_ValueIsOK() {
        final int value = 0;

        test.setY(value);

        Assert.assertEquals(value, test.getY());
    }

    @Test
    public void testHashcode_NoEquivalences() {
        //! Testvorbereitung
        Point[] points = new Point[4]; // 0 = lo | 1 = lu | 2 = ro | 3 = ru

        points[0] = new Point(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1);
        points[1] = new Point(Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1);
        points[2] = new Point(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1);
        points[3] = new Point(Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1);

        int[] hashes = new int[5]; // 4 = Mid
        hashes[0] = points[0].hashCode();
        hashes[1] = points[1].hashCode();
        hashes[2] = points[2].hashCode();
        hashes[3] = points[3].hashCode();
        hashes[4] = test.hashCode();

        //! Testausführung
        boolean result = true;

        for (int i = 0; i < hashes.length; i++) {
            for (int j = 0; j < hashes.length; j++) {
                if (i != j) {
                    if (hashes[i] == hashes[j])
                        result = false;
                }
            }
        }

        Assert.assertTrue("More than two point having the same hash.", result);
    }

    @Test
    public void testEquals_DifferentType() {
        Object differentObject = new Object();

        Assert.assertFalse("Types are different -> false.",
                test.equals(differentObject));
    }

    @Test
    public void testEquals_EqualObjects() {
        Point differentObject = new Point(0, 0);

        Assert.assertTrue("Points with same coordinates are equal.",
                test.equals(differentObject));
    }

    @Test
    public void testEquals_DifferentObjects() {
        Point differentObject = new Point(0, 1);

        Assert.assertFalse("Points with different coordinates are not equal.",
                test.equals(differentObject));
    }
}
