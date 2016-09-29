package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.OnGridUpdatedObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Used Observer pattern. Verify all the specified rules on any grid update.
 * Handle all the logic associated to the game rules.
 */
public class GridRuleManager implements OnGridUpdatedObserver {
    private Collection<GridRule> rules;
    private Collection<GameRulesObserver> observers = new ArrayList<>();

    public GridRuleManager() {
        rules = new ArrayList<>();
    }

    public GridRuleManager(Collection<GridRule> rules) {
        this.rules = rules;
    }

    public boolean addRule(GridRule<?> rule) {
        return this.rules.add(rule);
    }

    public boolean removeRule(GridRule<?> rule) {
        return this.rules.remove(rule);
    }

    @Override
    public void onGridUpdated(Grid grid) {
        this.rules.forEach(rule -> {
                try {
                    rule.verifyRule();
                } catch (RuleNotSatisfiedException e) {
                    notifyRuleUnsatisfied(e.getMessage());
                }
            }
        );

        if (grid.isComplete()) {
            notifyGameWon("The player has won the game!");
        }
    }

    public boolean addObserver(GameRulesObserver observer) {
        return this.observers.add(observer);
    }

    private void notifyRuleUnsatisfied(String message) {
        this.observers.forEach(o -> o.onRuleUnsatisfied(message));
    }

    private void notifyGameWon(String message) {
        this.observers.forEach(o -> o.onGameWon(message));
    }
}
