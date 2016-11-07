package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.OnGridUpdatedObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Used Observer pattern. Verify all the specified rules on any grid update.
 * Handle all the logic associated to the game rules.
 */
public class GridRuleManager implements OnGridUpdatedObserver {
    private Collection<GridRule> rules;
    private Collection<GridRule> winningRules;
    private Collection<GameRulesObserver> observers = new ArrayList<>();

    public GridRuleManager() {
        rules = new ArrayList<>();
        winningRules = new ArrayList<>();
    }

    public GridRuleManager(Collection<GridRule> rules, Collection<GridRule> winningRules) {
        this.rules = rules;
        this.winningRules = winningRules;
    }

    public boolean addRule(GridRule<?> rule) {
        return this.rules.add(rule);
    }

    public boolean removeRule(GridRule<?> rule) {
        return this.rules.remove(rule);
    }

    public boolean addWinningRule(GridRule<?> rule) {
        return this.winningRules.add(rule);
    }

    public boolean removeWinningRule(GridRule<?> rule) {
        return this.winningRules.remove(rule);
    }

    @Override
    public void onGridUpdated(Grid grid, Map<String, Object> extras) {
        boolean rulesOK = true;
        for (GridRule rule : rules) {
            try {
                rule.verifyRule();
            } catch (RuleNotSatisfiedException e) {
                rulesOK = false;
                updateExtras(extras);
                notifyRuleUnsatisfied(e.getMessage(), extras);
            } finally {
                rule.getRuleIterator().restart();
            }
        }

        if (rulesOK) {
            checkWinningRules();
        }
    }

    private void updateExtras(Map<String, Object> extras) {
        if (extras != null) {
            extras.put("validity", "invalid");
        }
    }

    public boolean addObserver(GameRulesObserver observer) {
        return this.observers.add(observer);
    }

    private void notifyRuleUnsatisfied(String message, Map<String, Object> extras) {
        this.observers.forEach(o -> o.onRuleUnsatisfied(message, extras));
    }

    private void checkWinningRules() {
        boolean winningRulesOK = false;
        for (GridRule winningRule : winningRules) {
            if (winningRule.shouldBeVerified()) {
                try {
                    winningRule.verifyRule();
                    winningRulesOK = true;
                } catch (RuleNotSatisfiedException e) {
                    winningRulesOK = false;
                    break;
                } finally {
                    winningRule.getRuleIterator().restart();
                }
            } else {
                winningRulesOK = false;
                break;
            }
        }
        if (winningRulesOK) {
            notifyGameWon("The player has won the game!");
        }
    }

    private void notifyGameWon(String message) {
        this.observers.forEach(o -> o.onGameWon(message));
    }
}
