package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;

/**
 * Content of a grid cell.
 * Works as a container for generic values.
 * @param <T> Type of the content.
 */
public abstract class Content<T> {

    protected T value;
    protected String tag;

    public Content(T value, String tag) {
        this.value = value;
        this.tag = tag;
    }

    public T getValue() {
        return value;
    }

    public abstract void setValue(T value) throws ImmutableContentValueException;

    public String getTag() {
        return tag;
    }

    public abstract boolean isValueEditable();

    public abstract boolean clearValue();

    public boolean isEmpty() {
        return value == null;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Content)) {
            return false;
        }
        Content otherContent = (Content)other;
        if (tag.equals(otherContent.getTag())) {
            if (value.equals(otherContent.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
