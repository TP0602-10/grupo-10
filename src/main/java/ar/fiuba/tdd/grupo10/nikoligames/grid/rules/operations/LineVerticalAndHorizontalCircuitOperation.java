package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;

/**
 * This rule checks if all Cell elements of the iterator form a line circuit.
 * Only vertical and horizontal line orientations are allowed.
 */
public class LineVerticalAndHorizontalCircuitOperation extends LineCircuitOperation {

    public LineVerticalAndHorizontalCircuitOperation(String tag) {
        super(tag);
    }

    @Override
    protected NeighbourPosition[] getAvailablePositions() {
        final NeighbourPosition[] availablePositions = {
                NeighbourPosition.TOP,
                NeighbourPosition.RIGHT,
                NeighbourPosition.BOTTOM,
                NeighbourPosition.LEFT};
        return availablePositions;
    }
}
