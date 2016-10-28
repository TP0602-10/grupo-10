package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types;

public class StringValue extends Value{
    public StringValue(String value) {
        super(value);
    }

    @Override
    protected void construct(String name) {
        this.value = name;
    }

}
