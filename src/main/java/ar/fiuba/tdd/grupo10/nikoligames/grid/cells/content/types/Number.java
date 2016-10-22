package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types;

public class Number extends Value<Integer> {

    public Number (String value){
        super(value);
    }

    @Override
    protected void construct(String value) {
        if (value != null) {
            this.value = Integer.parseInt(value);
        }
    }


}
