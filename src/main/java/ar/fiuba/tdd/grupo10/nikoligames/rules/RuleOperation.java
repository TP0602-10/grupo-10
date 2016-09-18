package ar.fiuba.tdd.grupo10.nikoligames.rules;

public interface RuleOperation<T> {
    T perform(RuleIterator iterator);
}
