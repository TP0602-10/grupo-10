package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers;

public class EqualsMatcher<T> implements GridRuleMatcher<T> {

    @Override
    public boolean matches(T o1, T o2) {
        return o1.equals(o2);
    }

    @Override
    public String getMatchingExplanation() {
        return "The matcher checks if the compared values are equal.";
    }

}
