package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line;


import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class Line implements ComparableNeighbour {


    @Override
    public boolean isValidOnTop() {
        return false;
    }

    @Override
    public boolean isValidOnBottom() {
        return false;
    }

    @Override
    public boolean isValidOnLeft() {
        return false;
    }

    @Override
    public boolean isValidOnRight() {
        return false;
    }

    @Override
    public boolean isValidOnTopRight() {
        return false;
    }

    @Override
    public boolean isValidOnBottomRight() {
        return false;
    }

    @Override
    public boolean isValidOnBottomLeft() {
        return false;
    }

    @Override
    public boolean isValidOnTopLeft() {
        return false;
    }
}
