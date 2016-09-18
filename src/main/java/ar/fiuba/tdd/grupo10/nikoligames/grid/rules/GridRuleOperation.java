package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

public interface GridRuleOperation<T> {
    T perform(GridRuleIterator iterator);
}
