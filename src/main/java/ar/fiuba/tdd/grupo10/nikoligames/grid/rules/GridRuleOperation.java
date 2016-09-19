package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

/**
 * Represents an operation to perform on each cell of a grid that returns an accumulative result.
 * @param <T> The type supported by the operation.
 */
public interface GridRuleOperation<T> {
    T perform(GridRuleIterator iterator);
}
