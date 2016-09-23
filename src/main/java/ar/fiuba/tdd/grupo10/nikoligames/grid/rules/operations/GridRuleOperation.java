package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

/**
 * Represents an operation to perform on a cell iterator that returns an accumulative result.
 * The perform() method accepts unlimited arguments besides the iterator on which it will apply the operation.
 * Each operation should know how to use this arguments -or not use them at all.
 * @param <R> The type of the operation result.
 */
public interface GridRuleOperation<R> {

    R perform(GridRuleIterator iterator, Object... params);

    boolean isApplicableOn(GridCell cell);

    String getOperationExplanation(R result);

}
