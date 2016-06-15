package de.tu.dresden.ifsr.kurs.java.roguelike.model.structures;


import de.tu.dresden.ifsr.kurs.java.roguelike.exceptions.InvalidPointException;
import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void testPoint() {
    }

    @Test
    public void testSetX() {
        try {
            Point test = new Point();
            test.setX(Integer.MIN_VALUE);

            Assert.fail();
        } catch (InvalidPointException e) {
            //Alles ok
        }
    }

    @Test(expected = InvalidPointException.class)
    public void testSetY() {
        Point test = new Point();
        test.setX(Integer.MIN_VALUE);
    }
}
