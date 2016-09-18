package ar.fiuba.tdd.grupo10.nikoligames.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GameGrid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.OnGridUpdatedObserver;

import java.util.ArrayList;
import java.util.Collection;

public class RuleManager implements OnGridUpdatedObserver {
    private Collection<Rule> rules;

    public RuleManager() {
        rules = new ArrayList<>();
    }

    public RuleManager(Collection<Rule> rules) {
        this.rules = rules;
    }

    public boolean addRule(Rule rule) {
        return this.rules.add(rule);
    }

    public boolean removeRule(Rule rule) {
        return this.rules.remove(rule);
    }

    @Override
    public void onUpdate(GameGrid grid) {
        this.rules.forEach(rule -> {
            try {
                rule.verifyRule(grid.getCells());
            } catch (RuleNotSatisfiedException e) {
                // TODO: 18/09/16 Decide what to do here.
            }
        });
    }
}
