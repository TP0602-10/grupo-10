package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import com.sun.istack.internal.NotNull;

/**
 * Content of a grid cell.
 * Works as a container for generic values.
 * Value cannot be null! Set the CellContent as null for this purpose instead.
 * @param <T> Type of the content that the cell can have
 */
public class CellContent<T> {
    private T value;

    public CellContent(@NotNull T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(@NotNull T value) {
        this.value = value;
    }

}
