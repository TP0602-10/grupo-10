package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types;

public class NullValue extends Value {

    public NullValue() {
        super("");
    }

    @Override
    protected void construct(String name) {
        this.value = null;
    }
}
