package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

/**
 * Content whose content can be changed.
 */
public class MutableContent<T> extends Content<T> {

    public MutableContent(T value, String tag) {
        super(value,tag);
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean isValueEditable() {
        return true;
    }

    @Override
    public boolean clearValue() {
        if (value != null) {
            value = null;
            return true;
        }
        return false;
    }
}
