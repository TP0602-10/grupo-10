package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Value;

/**
 * Content whose content can be changed.
 */
public class MutableContent<T> extends Content<T> {

    public MutableContent(Value<T> value, String tag) {
        super(value,tag);
    }

    @Override
    public void setValue(T value) {
        this.value.setValue(value);
    }

    @Override
    public void setValue(Value<T> value) throws ImmutableContentValueException {
        this.value = value;
    }

    @Override
    public boolean isValueEditable() {
        return true;
    }

    @Override
    public boolean clearValue() {
        return value.clearValue();
    }
}
