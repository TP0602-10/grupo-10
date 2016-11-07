package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

/**
 * A rule that must be satisfied within the game grid.
 * Each concrete rule must know whether it should be verified or not, accordingly with its context.
 * @param <T> The type of the result expected by the rule operation to compare it with the goal.
 */
public abstract class GridRule<T> {
    private final GridRuleIterator iterator;
    private final GridRuleOperation<T> operation;
    private GridRuleCondition<T> condition;

    public GridRule(GridRuleIterator iterator, GridRuleOperation<T> operation, GridRuleCondition<T> condition) {
        this.iterator = iterator;
        this.operation = operation;
        this.condition = condition;
    }

    public GridRuleIterator getIterator() {
        return iterator;
    }

    public void verifyRule() throws RuleNotSatisfiedException {
        if (shouldBeVerified()) {
            T operationResult = this.operation.perform(iterator);
            if (!doesOperationResultMatchesWithRuleGoal(operationResult)) {
                throw new RuleNotSatisfiedException(getRuleExplanation(operationResult));
            }
        }
    }

    public abstract boolean shouldBeVerified();

    public GridRuleIterator getRuleIterator() {
        return this.iterator;
    }

    private boolean doesOperationResultMatchesWithRuleGoal(T result) {
        return condition.doesValueMatchesWithRuleGoal(result);
    }

    private String getRuleExplanation(T operationResult) {
        return getRuleVerificationConditionExplanation()
                + " " + iterator.getCellsInvolvedExplanation()
                + " " + operation.getOperationExplanation(operationResult)
                + " " + condition.getConditionExplanation();
    }

    protected abstract String getRuleVerificationConditionExplanation();
}
