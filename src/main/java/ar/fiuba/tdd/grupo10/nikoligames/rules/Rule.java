package ar.fiuba.tdd.grupo10.nikoligames.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

import java.util.List;

public abstract class Rule<T> {
    private final RuleOperation<T> operation;
    private T goal;

    public Rule(RuleOperation<T> operation, T goal) {
        this.operation = operation;
        this.goal = goal;
    }

    public T getGoal() {
        return this.goal;
    }

    public void setGoal(T goal) {
        this.goal = goal;
    }

    public void verifyRule(List<List<GridCell>> cells) throws RuleNotSatisfiedException {
        RuleIterator iterator = getRuleIterator(cells);
        T operationResult = this.operation.perform(iterator);
        if ( !doesOperationResultMatchesWithRuleGoal(operationResult) ) {
            throw new RuleNotSatisfiedException(getRuleExplanation());
        }
    }

    protected abstract RuleIterator getRuleIterator(List<List<GridCell>> cells);

    protected abstract boolean doesOperationResultMatchesWithRuleGoal(T result);

    protected abstract String getRuleExplanation();
}
