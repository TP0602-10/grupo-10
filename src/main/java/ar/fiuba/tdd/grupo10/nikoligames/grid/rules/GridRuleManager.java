package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GameGrid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.OnGridUpdatedObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Used Observer pattern. Verify all the specified rules on any grid update.
 * Handle all the logic associated to the game rules.
 */
public class GridRuleManager implements OnGridUpdatedObserver {
    private Collection<GridRule<?>> rules;

    public GridRuleManager() {
        rules = new ArrayList<>();
    }

    public GridRuleManager(Collection<GridRule<?>> rules) {
        this.rules = rules;
    }

    public boolean addRule(GridRule<?> rule) {
        return this.rules.add(rule);
    }

    public boolean removeRule(GridRule<?> rule) {
        return this.rules.remove(rule);
    }

    @Override
    public void onGridUpdated(GameGrid grid) {
        this.rules.forEach(rule -> {
                try {
                    rule.verifyRule();
                } catch (RuleNotSatisfiedException e) {
                    // TODO: 18/09/16 Decide what to do here.
                }
            }
        );
    }
}
