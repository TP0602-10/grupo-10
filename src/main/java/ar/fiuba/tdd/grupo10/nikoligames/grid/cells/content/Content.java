package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Value;

import java.util.Objects;

/**
 * Content of a grid cell.
 * Works as a container for generic values.
 * @param <T> Type of the content.
 */
public abstract class Content<T> {

    protected Value<T> value;
    protected String tag;

    public Content(Value<T> value, String tag) {
        this.value = value;
        this.tag = tag;
    }

    public T getValue() {
        return value.getValue();
    }

    public Value<T> getValueObject() {
        return value;
    }

    public abstract void setValue(T value) throws ImmutableContentValueException;

    public abstract void setValue(Value<T> value) throws ImmutableContentValueException;


    public String getTag() {
        return tag;
    }

    public abstract boolean isValueEditable();

    public abstract boolean clearValue();

    public boolean isEmpty() {
        return value == null || value.getValue() == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Content<?> content = (Content<?>) obj;
        if (Objects.equals(tag, content.getTag())) {
            if (value != null ) {
                return value.equals(content.getValueObject());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, tag);
    }
}
