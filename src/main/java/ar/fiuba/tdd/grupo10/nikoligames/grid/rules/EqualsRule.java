package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

/**
 * Rule that checks for equality between operation result and rule goal to know if the rule is satisfied.
 * @param <T> Type of the rule operation result.
 */
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
