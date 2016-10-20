package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class TopLeftNeighbour implements NeighbourType {
    @Override
    public boolean isValid(ComparableNeighbour fromNeighbour, ComparableNeighbour toNeighbour) {
        return fromNeighbour.isValidOnTopLeft() && toNeighbour.isValidOnBottomRight();
    }
}
