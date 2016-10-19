package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable.ComparableNeighbour;

public interface NeighbourType {

    boolean isValid(ComparableNeighbour fromNeighbour, ComparableNeighbour toNeighbour);

}
