package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.*;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.*;
import java.util.stream.Collectors;

public class BoundariesManager {
    // TODO: 16/10/16 Set borders and corners when new neighbour is setted.
    private NeighbourContainer[] neighbours = new NeighbourContainer[NeighbourPosition.ALLOWED_POSITIONS];
    private NeighbourContainer[] limits = new NeighbourContainer[NeighbourPosition.ALLOWED_POSITIONS];

    public NeighbourType getNeighbourFrom(Cell otherCell) {
        for (NeighbourContainer neighbour : neighbours) {
            if ( neighbour != null ) {
                Cell neighbourCell = (Cell) neighbour.getNeighbourContainer();
                if (neighbourCell == otherCell) {
                    return neighbour.getNeighbourType();
                }
            }
        }
        return new InvalidNeighbour();
    }

    public NeighbourType getLimitFrom(Container otherBoundary) {
        for (NeighbourContainer boundary : limits) {
            if ( boundary != null ) {
                if (boundary.getNeighbourContainer() == otherBoundary) {
                    return boundary.getNeighbourType();
                }
            }
        }
        return new InvalidNeighbour();
    }

    public List<NeighbourContainer> getNeighbours() {
        return ListHelper.createListFromArray(neighbours);
    }

    public Container getNeighbourAt(NeighbourPosition position) {
        return getNeighbour( getNeighbourContainerAt(position) );
    }

    public NeighbourContainer getNeighbourContainerAt(NeighbourPosition position) {
        return neighbours[position.getIndex()];
    }

    public void setNeighbourAt(Cell actual, Cell neighbour, NeighbourPosition position) {
        NeighbourContainer neighbourContainer = new NeighbourContainer(neighbour, position.getType());
        if (!isNeighbourAlreadyAssigned(neighbourContainer)) {
            this.neighbours[position.getIndex()] = neighbourContainer;
            setSharedLimitsWithNeighbour(actual, neighbour, position);
            neighbour.setNeighbourAt(actual, position.getOpposite());
        }
    }

    private boolean isNeighbourAlreadyAssigned(NeighbourContainer neighbour) {
        return getNeighbours().contains(neighbour);
    }

    private void setSharedLimitsWithNeighbour(Cell actual, Cell neighbour, NeighbourPosition position) {
        Map<NeighbourPosition.RelativeType, List<NeighbourPosition>> frontiers
                = position.getAssociatedAndOppositeFrontiersInOrder();

        List<NeighbourPosition> associatedFrontier = frontiers.get(NeighbourPosition.RelativeType.ASSOCIATED);
        List<NeighbourPosition> oppositeFrontier = frontiers.get(NeighbourPosition.RelativeType.OPPOSITE);

        for (int i = 0; i < associatedFrontier.size(); i++) {
            Container limitToSet = actual.getLimitAt(associatedFrontier.get(i));
            neighbour.setLimitAt(
                    limitToSet,
                    oppositeFrontier.get(i)
            );
        }
    }

    public List<NeighbourContainer> getLimits() {
        return ListHelper.createListFromArray(limits);
    }

    public Container getLimitAt(NeighbourPosition position) {
        return getNeighbour(limits[position.getIndex()]);
    }

    public void setLimitAt(Container limit, NeighbourPosition position) {
        this.limits[position.getIndex()] = new NeighbourContainer(limit, position.getType());
    }

    private Container getNeighbour(NeighbourContainer neighbourContainer) {
        return (neighbourContainer != null) ? neighbourContainer.getNeighbourContainer() : null;
    }
}
