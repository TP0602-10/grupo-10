package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;

/**
 * This rule checks if all Cell elements of the iterator form a line circuit.
 * All line orientations are allowed.
 */
public class LineAnyCircuitOperation extends LineCircuitOperation {

    public LineAnyCircuitOperation(String tag) {
        super(tag);
    }

    @Override
    protected NeighbourPosition[] getAvailablePositions() {
        return NeighbourPosition.values();
    }
}
