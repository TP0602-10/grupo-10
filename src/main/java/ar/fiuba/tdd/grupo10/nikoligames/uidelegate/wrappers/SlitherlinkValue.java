package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;

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
        Boolean bottom = Boolean.valueOf(String.valueOf(this.value[0]));
        Boolean left = Boolean.valueOf(String.valueOf(this.value[0]));
        Boolean right = Boolean.valueOf(String.valueOf(this.value[0]));
        return new Boolean[] {top, bottom, left, right};
    }

    @Override
    public boolean isEquivalentTo(Object otherValue) {
        if (otherValue == null) {
            return this.value == null;
        } else  {
            return this.value != null
                    && (this.value[0] ^ ((Boolean[]) otherValue)[0])
                    && (this.value[1] ^ ((Boolean[]) otherValue)[1])
                    && (this.value[2] ^ ((Boolean[]) otherValue)[2])
                    && (this.value[3] ^ ((Boolean[]) otherValue)[3]);
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
