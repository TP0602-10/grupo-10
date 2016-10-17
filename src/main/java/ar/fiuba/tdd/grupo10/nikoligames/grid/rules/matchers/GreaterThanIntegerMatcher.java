package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers;

public class GreaterThanIntegerMatcher<T extends Integer> implements GridRuleMatcher<T> {
    @Override
    public boolean matches(T value, T goal) {
        return Integer.compare(value, goal) > 0;
    }

    @Override
    public String getMatchingExplanation() {
        return "The matcher checks if the Integer value is greater than the goal.";
    }
}
