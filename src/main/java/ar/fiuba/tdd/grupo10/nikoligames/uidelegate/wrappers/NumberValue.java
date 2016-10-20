package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

public class NumberValue implements PossibleValue {

    private Integer value;

    public NumberValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isEquivalentTo(Object otherValue) {
        if (otherValue == null) {
            return this.value == null;
        } else {
            return otherValue.equals(this.value);
        }
    }

    @Override
    public void setValueInCell(Cell cell) {
        if (value != null) {
            String stringValue = String.valueOf(value);
            cell.setValue(Integer.valueOf(stringValue));
        } else {
            cell.setValue(null);
        }
    }

}
