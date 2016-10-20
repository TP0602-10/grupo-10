package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;

public class PossibleValue<T> {

    private T value;

    public PossibleValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isEquivalentTo(Object otherValue) {
        if (otherValue == null) {
            return this.value == null;
        } else if (otherValue instanceof Line && this.value != null){
            return otherValue.getClass().toString().equals(this.value.getClass().toString());
        } else {
            return otherValue.equals(this.value);
        }
    }

}
