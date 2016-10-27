package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;

import java.util.Arrays;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.BORDER;

public class SlitherlinkValue implements PossibleValue {

    private Boolean[] value;

    public SlitherlinkValue(Boolean[] value) {
        if (value != null) {
            this.value = value.clone();
        }
    }

    @Override
    public Boolean[] getValue() {
        if (value == null) {
            return null;
        }
        Boolean top = Boolean.valueOf(String.valueOf(this.value[0]));
        Boolean bottom = Boolean.valueOf(String.valueOf(this.value[1]));
        Boolean left = Boolean.valueOf(String.valueOf(this.value[2]));
        Boolean right = Boolean.valueOf(String.valueOf(this.value[3]));
        return new Boolean[] {top, bottom, left, right};
    }

    @Override
    public boolean isEquivalentTo(Object otherValue) {
        if (otherValue == null) {
            return this.value == null;
        } else  {
            Boolean[] booleanArray = Arrays.copyOf(((Object[]) otherValue), ((Object[]) otherValue).length, Boolean[].class);
            return this.value != null
                    && (this.value[0].booleanValue() == booleanArray[0].booleanValue())
                    && (this.value[1].booleanValue() == booleanArray[1].booleanValue())
                    && (this.value[2].booleanValue() == booleanArray[2].booleanValue())
                    && (this.value[3].booleanValue() == booleanArray[3].booleanValue());
        }
    }


    @Override
    public void setValueInCell(Cell cell) {
        cell.setValue(this.value, "LINE");
        setValueInCell(cell, NeighbourPosition.TOP, 0);
        setValueInCell(cell, NeighbourPosition.BOTTOM, 1);
        setValueInCell(cell, NeighbourPosition.LEFT, 2);
        setValueInCell(cell, NeighbourPosition.RIGHT, 3);

    }

    private void setValueInCell(Cell cell, NeighbourPosition neighbourPosition, int arrayIndex) {
        Container container = cell.getLimitAt(neighbourPosition);
        if (container != null) {
            container.setValue(this.value[arrayIndex], BORDER);
        }
    }

}
