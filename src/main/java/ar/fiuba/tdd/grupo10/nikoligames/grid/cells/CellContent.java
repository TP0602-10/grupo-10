package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

/**
 * Generic content for a grid cell.
 * This serves as a container and allows to change the content dynamically.
 * @param <T> Type of the content that the cell can have
 */
public class CellContent<T> {
    private T value;

    public CellContent(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
