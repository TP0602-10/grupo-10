package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;

/**
 * This rule should ALWAYS be verified.
 * @param <T> The type of the result expected by the rule operation to compare it with the goal.
 */
public class AlwaysVerifiableRule<T> extends GridRule<T> {

    public AlwaysVerifiableRule(GridRuleIterator iterator, GridRuleOperation<T> operation, GridRuleCondition<T> condition) {
        super(iterator, operation, condition);
    }

    @Override
    public boolean shouldBeVerified() {
        return true;
    }

    @Override
    protected String getRuleVerificationConditionExplanation() {
        return "This rule should ALWAYS be verified.";
    }
}
