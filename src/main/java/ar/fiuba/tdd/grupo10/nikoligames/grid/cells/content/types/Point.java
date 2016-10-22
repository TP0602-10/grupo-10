package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class Point extends Value<Point> implements ComparableNeighbour {

    public Point() {
        super("");
    }

    @Override
    protected void construct(String name) {
        //this.value = null;
    }

    @Override
    public Point getValue() {
        //return the piont instance
        return this;
    }

    @Override
    public boolean isValidOnTop() {
        return true;
    }

    @Override
    public boolean isValidOnBottom() {
        return true;
    }

    @Override
    public boolean isValidOnLeft() {
        return true;
    }

    @Override
    public boolean isValidOnRight() {
        return true;
    }

    @Override
    public boolean isValidOnTopRight() {
        return true;
    }

    @Override
    public boolean isValidOnBottomRight() {
        return true;
    }

    @Override
    public boolean isValidOnBottomLeft() {
        return true;
    }

    @Override
    public boolean isValidOnTopLeft() {
        return true;
    }
}
