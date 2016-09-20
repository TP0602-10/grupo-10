package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

public class SumOperation implements GridRuleOperation<Integer> {

    @Override
    public java.lang.Integer perform(GridRuleIterator iterator) {
        Integer accSum = 0;
        while (iterator.hasNext()) {
            GridCell cell = iterator.next();
            if (isApplicableOn(cell)) {
                accSum += (Integer) cell.getContent().getValue();
            }
        }
        return accSum;
    }

    @Override
    public boolean isApplicableOn(GridCell cell) {
        return cell.areRulesApplicable()
                && cell.getContent() != null
                && cell.getContent().getValue() instanceof Integer;
    }

    @Override
    public String getOperationExplanation() {
        return "The operation sums all the Integer cells.";
    }
}
