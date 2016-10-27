package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;


public class CheckTopBorderOperation extends CheckBorderOperation {
    public CheckTopBorderOperation(String tag) {
        super(tag, NeighbourPosition.TOP);
    }

    @Override
    public String getOperationExplanation(Boolean result) {
        return "This operation checks whether the TOP border of a given set of cells has been set";
    }
}
