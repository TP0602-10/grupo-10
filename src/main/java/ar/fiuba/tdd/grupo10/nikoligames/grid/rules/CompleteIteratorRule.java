package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

/**
 * This rule should be verified ONLY when all the editable contents of the iterator are filled.
 * @param <T> The type of the result expected by the rule operation to compare it with the goal.
 */
public class CompleteIteratorRule<T> extends GridRule<T> {

    public CompleteIteratorRule(GridRuleIterator iterator, GridRuleOperation<T> operation, GridRuleCondition<T> condition) {
        super(iterator, operation, condition);
    }

    @Override
    public boolean shouldBeVerified() {
        return GridRuleIteratorHelper.listAllCells(getIterator())
                .stream()
                .allMatch(cell -> !cell.isContentEditable() || cell.isCompletelyFilled());
    }

    @Override
    protected String getRuleVerificationConditionExplanation() {
        return "This rule should be verified ONLY when all the editable contents of the iterator are filled.";
    }
}
