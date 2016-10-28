package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.LINE;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.NUMBER;

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
        } else if (this.value != null) {
            return otherValue.getClass().toString().equals(this.value.getClass().toString());
        }
        return false;
    }

    @Override
    public void setValueInCell(Cell cell) {
        cell.setValue(value, LINE);
    }

}
