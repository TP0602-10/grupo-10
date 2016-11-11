package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class LeftNeighbour implements NeighbourType {

    @SuppressWarnings("CPD-START")
    @Override
    public boolean isValid(ComparableNeighbour fromNeighbour, ComparableNeighbour toNeighbour) {
        return (fromNeighbour.isValidOnLeft() && toNeighbour.isValidOnRight())
                || (fromNeighbour.isValidOnBottomLeft() && toNeighbour.isValidOnBottomRight())
                || (fromNeighbour.isValidOnTopLeft() && toNeighbour.isValidOnTopRight());
    }
}
