package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Builder for making the game grid setting easier.
 */
public class GridBuilder {
    private int rows;
    private int columns;
    private List<GridCell> cells = new ArrayList<>();
    private Collection<OnGridUpdatedObserver> observers = new ArrayList<>();

    public GridBuilder() {}

    public GridBuilder setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public GridBuilder setColumns(int columns) {
        this.columns = columns;
        return this;
    }

    public GridBuilder addCells(List<GridCell> cells) {
        this.cells.addAll(cells);
        return this;
    }

    public GridBuilder addCell(GridCell cell) {
        this.cells.add(cell);
        return this;
    }

    public GridBuilder addObservers(Collection<OnGridUpdatedObserver> observers) {
        this.observers.addAll(observers);
        return this;
    }

    public GridBuilder addObserver(OnGridUpdatedObserver observer) {
        this.observers.add(observer);
        return this;
    }

    public Grid buildGrid() {
        try {
            validateDimensions();
        } catch (WrongNumberOfGridCellsException e) {
            // TODO: 18/09/16 Decide what to do in this case.
        }
        List<List<GridCell>> grid = ListHelper.buildMatrixFromFlattenList(cells, rows, columns);
        return new Grid(grid);
    }

    private void validateDimensions() throws WrongNumberOfGridCellsException {
        if (cells.size() != rows * columns) {
            throw new WrongNumberOfGridCellsException("The number of cells must match the grid dimensions: " + rows + "*" + columns);
        }
    }

}
