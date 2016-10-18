package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.InvalidNeighbour;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.NeighbourType;

public class NeighbourContainer {
    private Container neighbour;
    private NeighbourType type;

    public NeighbourContainer() {
        neighbour = null;
        type = new InvalidNeighbour();
    }

    public NeighbourContainer(Container container, NeighbourType neighbourtype) {
        neighbour = container;
        type = neighbourtype;
    }

    public NeighbourType getNeighbourType() {
        return type;
    }

    public Container getNeighbourContainer() {
        return neighbour;
    }

}
