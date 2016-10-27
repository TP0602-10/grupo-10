package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;


public class CheckBottomBorderOperation extends CheckBorderOperation {
    public CheckBottomBorderOperation(String tag) {
        super(tag, NeighbourPosition.BOTTOM);
    }

    @Override
    public String getOperationExplanation(Boolean result) {
        return "This operation checks whether the BOTTOM border of a given set of cells has been set";
    }
}
