package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

import java.util.List;

/**
 * Equals rule that iterate over a specified row to apply conditions.
 * @param <T> Type of the rule operation result.
 */
public class RowEqualsRule<T> extends EqualsRule<T> {
    private final int rowNumber;

    public RowEqualsRule(GridRuleOperation<T> operation, T goal, int rowNumber) {
        super(operation, goal);
        this.rowNumber = rowNumber;
    }

    @Override
    protected GridRuleIterator getRuleIterator(List<List<GridCell>> cells) {
        return new GridRuleIterator(cells.get(rowNumber));
    }

    @Override
    protected String getCellsInvolvedExplanation() {
        return "The rule must be satisfied for the row " + rowNumber + ".";
    }

}
