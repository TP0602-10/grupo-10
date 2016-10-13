package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import java.util.Map;

/**
 * All classes that wants to be notified when a rule fails.
 */
public interface GameRulesObserver {
    void onRuleUnsatisfied(String message, Map<String, Object> extras);

    void onGameWon(String message);
}
