package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.GridRuleMatcher;

public class GridRuleCondition<T> {
    private final GridRuleMatcher<T> matcher;
    private final T goal;

    public GridRuleCondition(GridRuleMatcher<T> matcher, T goal) {
        this.matcher = matcher;
        this.goal = goal;
    }

    boolean doesValueMatchesWithRuleGoal(T value) {
        return matcher.matches(value, goal);
    }

    public String getConditionExplanation() {
        return matcher.getMatchingExplanation()
                + " The rule goal is " + goal.toString() + ".";
    }
}
