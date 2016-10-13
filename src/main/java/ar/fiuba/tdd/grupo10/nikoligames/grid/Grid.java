package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GameRulesObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Game main board. It contains cells organized by rows and columns.
 * Used Observable pattern. It can subscribe to new observers and notify them when a change it is made on the grid.
 */
public class Grid implements GameRulesObserver {
    private final List<List<Cell>> cells;
    private Collection<OnGridUpdatedObserver> observers;
    private Collection<GameRulesObserver> ruleObservers = new ArrayList<>();

    public Grid(List<List<Cell>> cells, Collection<OnGridUpdatedObserver> observers) {
        this.cells = cells;
        this.observers = observers;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public Cell getCellAt(int row, int column) {
        return this.cells.get(row).get(column);
    }

    public boolean addObserver(OnGridUpdatedObserver observer) {
        return this.observers.add(observer);
    }

    public void notifyGridUpdated(Map<String, Object> extras) {
        this.observers.forEach(o -> o.onGridUpdated(this, extras));
    }

    public void notifyRuleUnsatisfied(String message, Map<String, Object> extras) {
        this.ruleObservers.forEach(o -> o.onRuleUnsatisfied(message, extras));
    }

    public void notifyGameWon(String message) {
        this.ruleObservers.forEach(o -> o.onGameWon(message));
    }

    @Override
    public void onRuleUnsatisfied(String message, Map<String, Object> extras) {
        notifyRuleUnsatisfied(message, extras);
    }

    @Override
    public void onGameWon(String message) {
        notifyGameWon(message);
    }

    public void addRuleObserver(GameRulesObserver ruleObserver) {
        this.ruleObservers.add(ruleObserver);
    }

    public boolean isComplete() {
        return cells.stream()
                .allMatch(row ->
                        row.stream()
                                .filter(Cell::isContentEditable)
                                .allMatch(Cell::isCompletelyFilled)
                );
    }
}
