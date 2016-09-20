package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import java.util.Objects;

/**
 * Content of a grid cell.
 * Works as a container for generic values.
 * @param <T> Type of the content that the cell can have
 */
public class CellContent<T> {
    private T value;

    public CellContent(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CellContent<?> that = (CellContent<?>) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
