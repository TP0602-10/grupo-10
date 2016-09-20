package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

public class EqualsRowRule<T> extends RowRule<T> {

    public EqualsRowRule(GridRuleOperation<T> operation, T goal, int rowNumber) {
        super(operation, goal, rowNumber);
    }

    @Override
    protected boolean doesOperationResultMatchesWithRuleGoal(T result) {
        return getGoal().equals(result);
    }

    @Override
    protected String getResultExpectedExplanation() {
        return "The operation result must be EQUAL to " + getGoal().toString();
    }
}
