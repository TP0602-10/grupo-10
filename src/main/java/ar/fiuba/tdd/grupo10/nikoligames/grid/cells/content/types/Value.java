package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types;

import java.util.Objects;

public abstract class Value<T> {

    protected T value;

    public Value(String name) {
        construct(name);
    }

    protected abstract void construct(String name);

    public T getValue(){
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }

    public boolean clearValue(){
        if (value == null){
            return false;
        }
        else{
            value = null;
            return true;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Value val = (Value) obj;
        return (Objects.equals(this.value,val.getValue()));
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
