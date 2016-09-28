package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;

/**
 * Content whose content cannot be changed.
 */
public class ImmutableContent<T> extends Content<T> {

    public ImmutableContent(T value, String tag) {
        super(value,tag);
    }

    @Override
    public void setValue(T value) throws ImmutableContentValueException {
        throw new ImmutableContentValueException("value of content immutable.");
    }

    @Override
    public boolean isValueEditable() {
        return false;
    }

    @Override
    public boolean clearValue() {
        return false;
    }

}
