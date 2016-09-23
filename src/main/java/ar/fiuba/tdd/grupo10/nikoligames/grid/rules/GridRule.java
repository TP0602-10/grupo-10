package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

import java.util.List;

/**
 * A rule that must be satisfied within the game grid.
 * @param <T> The type of the result expected by the rule operation to compare it with the goal.
 */
public class GridRule<T> {
    private final GridRuleIterator iterator;
    private final GridRuleOperation<T> operation;
    private GridRuleCondition<T> condition;

    public GridRule(GridRuleIterator iterator, GridRuleOperation<T> operation, GridRuleCondition<T> condition) {
        this.iterator = iterator;
        this.operation = operation;
        this.condition = condition;
    }

    public void verifyRule() throws RuleNotSatisfiedException {
        T operationResult = this.operation.perform(iterator);
        if ( !doesOperationResultMatchesWithRuleGoal(operationResult) ) {
            throw new RuleNotSatisfiedException(getRuleExplanation(operationResult));
        }
    }

    public GridRuleIterator getRuleIterator() {
        return this.iterator;
    }

    private boolean doesOperationResultMatchesWithRuleGoal(T result) {
        return condition.doesValueMatchesWithRuleGoal(result);
    }

    private String getRuleExplanation(T operationResult) {
        return iterator.getCellsInvolvedExplanation()
                + " " + operation.getOperationExplanation(operationResult)
                + " " + condition.getConditionExplanation();
    }
}
