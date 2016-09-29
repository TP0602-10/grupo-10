package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.OnRuleUnsatisfiedObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Game main board. It contains cells organized by rows and columns.
 * Used Observable pattern. It can subscribe to new observers and notify them when a change it is made on the grid.
 */
public class Grid implements OnRuleUnsatisfiedObserver {
    private final List<List<Cell>> cells;
    private Collection<OnGridUpdatedObserver> observers;
    private Collection<OnRuleUnsatisfiedObserver> ruleObservers = new ArrayList<>();

    public Grid(List<List<Cell>> cells) {
        this.cells = cells;
    }

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

    public void notifyGridUpdated() {
        this.observers.forEach(o -> o.onGridUpdated(this));
    }

    public void notifyRuleUnsatisfied(String message) {
        this.ruleObservers.forEach(o -> o.onRuleUnsatisfied(message));
    }

    @Override
    public void onRuleUnsatisfied(String message) {
        notifyRuleUnsatisfied(message);
    }

    public void addRuleObserver(OnRuleUnsatisfiedObserver ruleObserver) {
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
