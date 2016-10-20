package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.InvalidNeighbour;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.NeighbourType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        NeighbourContainer that = (NeighbourContainer) other;
        return Objects.equals(neighbour, that.neighbour) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(neighbour, type);
    }
}
