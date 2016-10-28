package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class BottomNeighbour implements NeighbourType {
    @Override
    public boolean isValid(ComparableNeighbour fromNeighbour, ComparableNeighbour toNeighbour) {
        return fromNeighbour.isValidOnBottom() && toNeighbour.isValidOnTop();
    }
}
