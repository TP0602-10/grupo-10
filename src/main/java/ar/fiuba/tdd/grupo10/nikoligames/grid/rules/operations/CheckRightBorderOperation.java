package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;


public class CheckRightBorderOperation extends CheckBorderOperation {
    public CheckRightBorderOperation(String tag) {
        super(tag, NeighbourPosition.RIGHT);
    }



    @Override
    public String getOperationExplanation(Boolean result) {
        return "This operation checks whether the series of cells passed have their right border marked";
    }
}
