package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

public interface CellState {

    CellContent getContent();

    void setContent(CellContent content, GridCell context);

    void clearContent(GridCell cell);

}
