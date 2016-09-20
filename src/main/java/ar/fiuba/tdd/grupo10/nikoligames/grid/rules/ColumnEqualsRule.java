package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

import java.util.ArrayList;
import java.util.List;

public class ColumnEqualsRule<T> extends EqualsRule<T> {
    private final int columnNumber;

    public ColumnEqualsRule(GridRuleOperation<T> operation, T goal, int columnNumber) {
        super(operation, goal);
        this.columnNumber = columnNumber;
    }

    @Override
    protected GridRuleIterator getRuleIterator(List<List<GridCell>> cells) {
        List<GridCell> column = new ArrayList<>();
        cells.forEach(row -> column.add(row.get(columnNumber)));
        return new GridRuleIterator(column);
    }

    @Override
    protected String getCellsInvolvedExplanation() {
        return "The rule must be satisfied for the column " + columnNumber + ".";
    }
}
