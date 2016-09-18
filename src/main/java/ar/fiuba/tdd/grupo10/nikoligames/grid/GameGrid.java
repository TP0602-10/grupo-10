package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameGrid {
    private final List<List<GridCell>> cells;
    private Collection<OnGridUpdatedObserver> observers = new ArrayList<>();

    public GameGrid(List<List<GridCell>> cells) {
        this.cells = cells;
    }

    public GameGrid(List<List<GridCell>> cells, Collection<OnGridUpdatedObserver> observers) {
        this.cells = cells;
        this.observers = observers;
    }

    public List<List<GridCell>> getCells() {
        return cells;
    }

    public GridCell getCellAt(int row, int column) {
        return this.cells.get(row).get(column);
    }

    public boolean addObserver(OnGridUpdatedObserver observer) {
        return this.observers.add(observer);
    }

    public void notifyGridUpdated() {
        this.observers.forEach(o -> o.onUpdate(this));
    }
}
