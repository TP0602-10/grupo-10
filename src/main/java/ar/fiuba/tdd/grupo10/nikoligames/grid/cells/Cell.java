package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic structure of the cell.
 */
public class Cell extends Container {

    private final BoundariesManager boundaries;

    public Cell(ContainerState state) {
        super(state);
        this.boundaries = new BoundariesManager();
    }

    public NeighbourType getNeighbourFrom(Cell otherCell) {
        return boundaries.getNeighbourFrom(otherCell);
    }

    public NeighbourType getLimitFrom(Container limit) {
        return boundaries.getLimitFrom(limit);
    }

    public Container getNeighbourAt(NeighbourPosition position) {
        return boundaries.getNeighbourAt(position);
    }

    public void setNeighbourAt(Container neighbour, NeighbourPosition position) {
        boundaries.setNeighbourAt(neighbour, position);
    }

    public Container getLimitAt(NeighbourPosition position) {
        return boundaries.getLimitAt(position);
    }

    public void setLimitAt(Container limit, NeighbourPosition position) {
        boundaries.setLimitAt(limit, position);
    }

    public List<NeighbourContainer> getNeighboursCells() {
        List<NeighbourContainer> neighbourCells = new ArrayList<>();
        neighbourCells.add( leftNeighbour );
        neighbourCells.add( topNeighbour );
        neighbourCells.add( rightNeighbour );
        neighbourCells.add( bottomNeighbour );
        return neighbourCells;
    }

}
