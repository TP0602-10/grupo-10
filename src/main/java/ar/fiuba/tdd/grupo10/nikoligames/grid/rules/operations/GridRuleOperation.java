package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

/**
 * Represents an operation to perform on a cell iterator that returns an accumulative result.
 * The operation will know also which contents of those cells should use (it uses strings as content tags).
 * The perform() method accepts unlimited arguments besides the iterator on which it will apply the operation.
 * Each operation should know how to use this arguments -or not use them at all.
 * @param <R> The type of the operation result.
 */
public abstract class GridRuleOperation<R> {
    private final List<String> contentTags;

    public GridRuleOperation(List<String> contentTags) {
        this.contentTags = contentTags;
    }

    protected List<String> getContentTags() {
        return contentTags;
    }

    public abstract R perform(GridRuleIterator iterator, Object... params);

    public abstract boolean isApplicableOn(GridCell cell);

    public abstract String getOperationExplanation(R result);

}
