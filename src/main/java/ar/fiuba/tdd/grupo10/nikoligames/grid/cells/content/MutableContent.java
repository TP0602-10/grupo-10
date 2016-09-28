package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

/**
 * Content whose content can be changed.
 */
public class MutableContent<T> implements Content<T> {

    private T value;
    private String tag;

    public MutableContent(T value, String tag) {
        this.value = value;
        this.tag = tag;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public String getTag() {
        return tag;
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

    @Override
    public boolean isEmpty() {
        return value == null;
    }
}
