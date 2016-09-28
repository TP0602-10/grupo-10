package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;

/**
 * Content of a grid cell.
 * Works as a container for generic values.
 * @param <T> Type of the content.
 */
public interface Content<T> {

    T getValue();

    void setValue(T value) throws ImmutableContentValueException;

    String getTag();

    boolean isValueEditable();

    //TODO: [Tomi-Sep27] Why is boolean? If it is clean-empty, it can be cleaned (the method does nothing).
    boolean clearValue();

    boolean isEmpty();
}
