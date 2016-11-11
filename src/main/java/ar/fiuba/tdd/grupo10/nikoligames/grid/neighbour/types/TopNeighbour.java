package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class TopNeighbour implements NeighbourType {

    @SuppressWarnings("CPD-START")
    @Override
    public boolean isValid(ComparableNeighbour fromNeighbour, ComparableNeighbour toNeighbour) {
        return (fromNeighbour.isValidOnTop() && toNeighbour.isValidOnBottom())
                || (fromNeighbour.isValidOnTopLeft() && toNeighbour.isValidOnBottomLeft())
                || (fromNeighbour.isValidOnTopRight() && toNeighbour.isValidOnBottomRight());
    }
}
