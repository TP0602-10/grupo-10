package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

public interface CellState<T extends CellContent> {

    T getContent();

    void setContent(T content);

    void clearContent();

}
