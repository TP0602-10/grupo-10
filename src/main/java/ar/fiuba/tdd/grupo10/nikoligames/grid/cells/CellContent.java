package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

/**
 * Generic content for a grid cell.
 * @param <T> Type of the content that the cell can have
 */
public interface CellContent<T> {

    T getValue();

    void setValue(T value);

}
