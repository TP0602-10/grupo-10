package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

// TODO: 22/09/16 REFACTOR: Remove this class. GridRule should receive a RuleCondition [pending implementation!]
// that will contains the goal and also will know how to COMPARE the operation result with it.
/**
 * Rule that checks for equality between operation result and rule goal to know if the rule is satisfied.
 * @param <T> Type of the rule operation result.
 */
public class EqualsRule<T> extends GridRule<T> {
    public EqualsRule(GridRuleIterator iterator, GridRuleOperation<T> operation, T goal) {
        super(iterator, operation, goal);
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
