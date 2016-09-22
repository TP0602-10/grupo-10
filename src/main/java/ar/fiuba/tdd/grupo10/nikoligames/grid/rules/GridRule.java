package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

import java.util.List;

/**
 * A rule that must be satisfied within the game grid.
 * Used Template Method pattern.
 * The concrete rules must know how to iterate over the cells and how to check if the goal is reached.
 * For example, a rule would iterate all the cells of a row and check if the goal is equal to
 * the operation result.
 * @param <T> The type of the result expected by the rule operation to compare it with the goal.
 */
public abstract class GridRule<T> {
    private final GridRuleIterator iterator;
    private final GridRuleOperation<T> operation;
    private T goal;

    public GridRule(GridRuleIterator iterator, GridRuleOperation<T> operation, T goal) {
        this.iterator = iterator;
        this.operation = operation;
        this.goal = goal;
    }

    public GridRuleOperation<T> getOperation() {
        return this.operation;
    }

    public T getGoal() {
        return this.goal;
    }

    public void setGoal(T goal) {
        this.goal = goal;
    }

    public void verifyRule() throws RuleNotSatisfiedException {
        T operationResult = this.operation.perform(iterator);
        if ( !doesOperationResultMatchesWithRuleGoal(operationResult) ) {
            throw new RuleNotSatisfiedException(getRuleExplanation());
        }
    }

    public GridRuleIterator getRuleIterator() {
        return this.iterator;
    }

    protected abstract boolean doesOperationResultMatchesWithRuleGoal(T result);

    private String getRuleExplanation() {
        return iterator.getCellsInvolvedExplanation()
                + " " + operation.getOperationExplanation()
                + " " + getResultExpectedExplanation();
    }

    protected abstract String getResultExpectedExplanation();
}
