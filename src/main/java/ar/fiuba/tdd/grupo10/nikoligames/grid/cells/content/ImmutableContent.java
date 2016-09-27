package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;

/**
 * Content whose content cannot be changed.
 */
public class ImmutableContent<T> implements Content<T> {

    private String tag;
    private T value;

    public ImmutableContent(T value, String tag){
        this.value = value;
        this.tag = tag;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) throws ImmutableContentValueException {
        throw new ImmutableContentValueException("value of content immutable.");
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public boolean isValueEditable() {
        return false;
    }

    @Override
    public boolean clearValue() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return value == null;
    }
}
