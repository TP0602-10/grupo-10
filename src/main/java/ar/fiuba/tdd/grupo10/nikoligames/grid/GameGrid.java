package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

import java.util.List;

public class GameGrid {
    private final List<List<GridCell>> cells;

    public GameGrid(List<List<GridCell>> cells) {
        this.cells = cells;
    }

    public GridCell getCellAt(int row, int column) {
        return this.cells.get(row).get(column);
    }
}
