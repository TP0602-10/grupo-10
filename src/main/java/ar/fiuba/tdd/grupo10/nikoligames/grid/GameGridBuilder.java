package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for making the game grid setting easier.
 */
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
        try {
            validateDimensions();
        } catch (WrongNumberOfGridCellsException e) {
            // TODO: 18/09/16 Decide what to do in this case.
        }
        List<List<GridCell>> grid = buildCellGridFromCellFlattenList();
        return new GameGrid(grid);
    }

    private void validateDimensions() throws WrongNumberOfGridCellsException {
        if (cells.size() != rows * columns) {
            throw new WrongNumberOfGridCellsException("The number of cells must match the grid dimensions: " + rows + "*" + columns);
        }
    }

    private List<List<GridCell>> buildCellGridFromCellFlattenList() {
        List<List<GridCell>> grid = new ArrayList<>(rows);
        int cellsFromListAlreadyAdded = 0;
        for (int r = 0; r < rows; r++) {
            List<GridCell> row = new ArrayList<>(columns);
            int col;
            for (col = 0; col < columns; col++) {
                row.add( cells.get(cellsFromListAlreadyAdded + (r + col)) );
            }
            grid.add(row);
            cellsFromListAlreadyAdded += col - 1;
        }
        return grid;
    }

}