package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public class BottomNeighbour implements NeighbourType {
    @Override
    public boolean isValid(ComparableNeighbour neighbour1, ComparableNeighbour neighbout2) {
        return neighbout2.isValidOnTop();
    }
}
