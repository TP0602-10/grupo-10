package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Value;

public class StringValue extends Value{
    public StringValue(String value){
        super(value);
    }

    @Override
    protected void construct(String name) {
        this.value = name;
    }

}
