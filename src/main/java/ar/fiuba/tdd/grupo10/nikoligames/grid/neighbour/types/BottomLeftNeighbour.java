package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class BottomLeftNeighbour implements NeighbourType {
    @Override
    public boolean isValid(ComparableNeighbour neighbour1, ComparableNeighbour neighbour2) {
        return neighbour2.isValidOnTopRight();
    }
}
