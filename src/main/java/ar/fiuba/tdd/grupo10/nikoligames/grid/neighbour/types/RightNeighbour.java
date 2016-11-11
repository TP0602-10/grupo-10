package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class RightNeighbour implements NeighbourType {

    @SuppressWarnings("CPD-START")
    @Override
    public boolean isValid(ComparableNeighbour fromNeighbour, ComparableNeighbour toNeighbour) {
        return (fromNeighbour.isValidOnRight() && toNeighbour.isValidOnLeft())
                || (fromNeighbour.isValidOnTopRight() && toNeighbour.isValidOnTopLeft())
                || (fromNeighbour.isValidOnBottomRight() && toNeighbour.isValidOnBottomLeft());
    }
}
