package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

/**
 * Represents an operation to perform on a cell iterator that returns an accumulative result.
 * @param <T> The type of the operation result.
 */
public interface GridRuleOperation<T> {

    T perform(GridRuleIterator iterator);

    String getOperationExplanation();

}
