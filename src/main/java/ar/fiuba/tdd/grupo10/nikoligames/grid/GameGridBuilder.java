package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

import java.util.ArrayList;
import java.util.List;

public class GameGridBuilder {
    private int rows;
    private int columns;
    private List<GridCell> cells = new ArrayList<>();

    public GameGridBuilder() {}

    public GameGridBuilder setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public GameGridBuilder setColumns(int columns) {
        this.columns = columns;
        return this;
    }

    public GameGridBuilder setCells(List<GridCell> cells) {
        this.cells = cells;
        return this;
    }

    public GameGridBuilder addCell(GridCell cell) {
        this.cells.add(cell);
        return this;
    }

    public GameGrid buildGrid() {
        validateDimensions();
        GridCell[][] grid = buildCellMatrixFromCellFlattenList();
        return new GameGrid(grid);
    }

    private void validateDimensions() throws IllegalStateException {
        if (cells.size() != rows * columns) {
            throw new IllegalStateException("The number of cells must match the grid dimensions: " + rows + "*" + columns);
        }
    }

    private GridCell[][] buildCellMatrixFromCellFlattenList() {
        GridCell[][] matrix = new GridCell[rows][columns];
        int cellsFromListAlreadyAdded = 0;
        for (int r = 0; r < rows; r++) {
            int col;
            for (col = 0; col < columns; col++) {
                matrix[r][col] = cells.get(cellsFromListAlreadyAdded + (r + col));
            }
            cellsFromListAlreadyAdded += col - 1;
        }
        return matrix;
    }

}
