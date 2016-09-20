package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

/**
 * Represents an operation to perform on a cell iterator that returns an accumulative result.
 * @param <R> The type of the operation result.
 */
public interface GridRuleOperation<R> {

    R perform(GridRuleIterator iterator);

    boolean isApplicableOn(GridCell cell);

    String getOperationExplanation();

}
