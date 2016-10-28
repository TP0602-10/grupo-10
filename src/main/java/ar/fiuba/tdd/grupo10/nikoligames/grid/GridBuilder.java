package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
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
    private List<Cell> cells = new ArrayList<>();
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

    public GridBuilder addCells(List<Cell> cells) {
        this.cells.addAll(cells);
        return this;
    }

    public GridBuilder addCell(Cell cell) {
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

    public Grid buildGrid() throws WrongNumberOfGridCellsException {
        validateDimensions();
        List<List<Cell>> grid = ListHelper.buildMatrixFromFlattenList(cells, rows, columns);
        return new Grid(grid, observers);
    }

    private boolean isValidToTop(int index) {
        return ( index >= columns );
    }

    private boolean isValidToBottom(int index) {
        return ( index < (rows * (columns - 1 )) );
    }

    private boolean isValidToRight(int index) {
        return ( (index + 1) % columns != 0 );
    }

    private boolean isValidToLeft(int index) {
        return ( index % columns != 0 );
    }

    private void setTopNeighbour( int index ) {
        if ( isValidToTop(index) ) {
            cells.get(index).setNeighbourAt( cells.get(index - columns), NeighbourPosition.TOP );
        }
    }

    private void setBottomNeighbour( int index ) {
        if ( isValidToBottom(index) ) {
            cells.get(index).setNeighbourAt( cells.get(index + columns), NeighbourPosition.BOTTOM );
        }
    }

    private void setLeftNeighbour( int index ) {
        if ( isValidToLeft(index) ) {
            cells.get(index).setNeighbourAt( cells.get(index - 1), NeighbourPosition.LEFT );
        }
    }

    private void setRightNeighbour( int index ) {
        if ( isValidToRight(index) ) {
            cells.get(index).setNeighbourAt( cells.get(index + 1), NeighbourPosition.RIGHT );
        }
    }

    private void setTopRightNeighbour(int index) {
        if ( isValidToTop(index) && isValidToRight(index) ) {
            cells.get(index).setNeighbourAt( cells.get(index - columns + 1), NeighbourPosition.TOP_RIGHT );
        }
    }

    private void setBottomRightNeighbour(int index) {
        if ( isValidToBottom(index) && isValidToRight(index) ) {
            cells.get(index).setNeighbourAt( cells.get(index + columns + 1), NeighbourPosition.BOTTOM_RIGHT );
        }
    }

    private void setBottomLeftNeighbour(int index) {
        if ( isValidToBottom(index) && isValidToLeft(index) ) {
            cells.get(index).setNeighbourAt( cells.get(index + columns - 1), NeighbourPosition.BOTTOM_LEFT );
        }
    }

    private void setTopLeftNeighbour(int index) {
        if ( isValidToTop(index) && isValidToLeft(index) ) {
            cells.get(index).setNeighbourAt( cells.get(index - columns - 1), NeighbourPosition.TOP_LEFT );
        }
    }

    private void setCornerNeighbours(int index) {
        setTopRightNeighbour(index);
        setBottomRightNeighbour(index);
        setBottomLeftNeighbour(index);
        setTopLeftNeighbour(index);
    }


    public GridBuilder doNeighborlyRelations() throws WrongNumberOfGridCellsException {
        validateDimensions();
        for (int i = 0; i < cells.size(); i++) {
            setTopNeighbour(i);
            setRightNeighbour(i);
            setBottomNeighbour(i);
            setLeftNeighbour(i);
            setCornerNeighbours(i);
        }
        return this;


    }

    private void validateDimensions() throws WrongNumberOfGridCellsException {
        if (cells.size() != rows * columns) {
            throw new WrongNumberOfGridCellsException("The number of cells must match the grid dimensions: " + rows + "*" + columns);
        }
    }



}
