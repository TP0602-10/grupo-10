package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers;

public interface GridRuleMatcher<T> {

    boolean matches(T o1, T o2);

    String getMatchingExplanation();

}
