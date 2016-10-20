package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;

public class LineValue implements PossibleValue {

    private Line value;

    public LineValue(Line value) {
        this.value = value;
    }

    public Line getValue() {
        return value;
    }

    public boolean isEquivalentTo(Object otherValue) {
        if (otherValue == null) {
            return this.value == null;
        } else if (this.value != null){
            return otherValue.getClass().toString().equals(this.value.getClass().toString());
        }
        return false;
    }

}
