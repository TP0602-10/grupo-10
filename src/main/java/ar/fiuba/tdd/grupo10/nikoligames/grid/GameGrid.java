package ar.fiuba.tdd.grupo10.nikoligames.grid;

public class GameGrid {
    private final GridCell[][] cells;

    public GameGrid(GridCell[][] cells) {
        this.cells = cells;
    }

    public GridCell getCellAt(int row, int column) {
        return cells[row][column];
    }
}
