package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers;

public class LessOrEqualToIntegerMatcher<T extends Integer> implements GridRuleMatcher<T> {
    @Override
    public boolean matches(T value, T goal) {
        return value.compareTo(goal) <= 0;
    }

    @Override
    public String getMatchingExplanation() {
        return "The matcher checks if the Integer value is less or equal to the goal.";
    }
}
