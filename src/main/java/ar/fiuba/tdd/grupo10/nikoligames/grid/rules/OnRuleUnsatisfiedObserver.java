package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

/**
 * All classes that wants to be notified when a rule fails.
 */
public interface OnRuleUnsatisfiedObserver {
    void onRuleUnsatisfied(String message);
}
