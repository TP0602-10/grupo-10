package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRule;

/**
 * State of a cell in a certain moment. It contains the cell content.
 * State pattern used to allow change states at runtime.
 */
public interface CellState {

    CellContent getContent();

    void setContent(CellContent content, GridCell context);

    void clearContent(GridCell cell);

    boolean areRulesApplicable();

}
