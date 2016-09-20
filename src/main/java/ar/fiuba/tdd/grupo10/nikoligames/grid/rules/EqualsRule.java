package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

public abstract class EqualsRule<T> extends GridRule<T> {
    public EqualsRule(GridRuleOperation<T> operation, T goal) {
        super(operation, goal);
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
