package de.tu.dresden.ifsr.kurs.java.roguelike.model;


import de.tu.dresden.ifsr.kurs.java.roguelike.model.structures.Point;

public interface VisibleObject {

    public String getVisibleCharacter();

    public Point getPosition();

    public void setPosition(Point position);

    public void move();
}
