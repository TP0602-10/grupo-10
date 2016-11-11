package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class BottomNeighbour implements NeighbourType {

    @SuppressWarnings("CPD-START")
    @Override
    public boolean isValid(ComparableNeighbour fromNeighbour, ComparableNeighbour toNeighbour) {
        return (fromNeighbour.isValidOnBottom() && toNeighbour.isValidOnTop())
                || (fromNeighbour.isValidOnBottomRight() && toNeighbour.isValidOnTopRight())
                || (fromNeighbour.isValidOnBottomLeft() && toNeighbour.isValidOnTopLeft());
    }
}
