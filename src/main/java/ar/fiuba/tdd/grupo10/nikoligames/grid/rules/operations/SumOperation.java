package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

/**
 * Rule operation that returns the sum of all the content cells.
 * Content cells evaluated must be Integer and the operation result is Integer as well.
 */
public class SumOperation implements GridRuleOperation<Integer> {

    @Override
    public java.lang.Integer perform(GridRuleIterator iterator, Object... params) {
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
    public String getOperationExplanation(Integer result) {
        return "The operation sums all the Integer cells. The result is " + result.toString() + ".";
    }
}
