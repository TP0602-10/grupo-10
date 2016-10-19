package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class Point implements ComparableNeighbour {
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
