package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

public class CellContent<T> {
    private T value;

    public CellContent(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
