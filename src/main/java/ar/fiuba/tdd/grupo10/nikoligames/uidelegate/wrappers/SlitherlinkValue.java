package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.NullValue;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.HorizontalLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.VerticalLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        cell.setValue(this.value, "BORDER");
        setValueInCell(cell, NeighbourPosition.TOP, 0);
        setValueInCell(cell, NeighbourPosition.BOTTOM, 1);
        setValueInCell(cell, NeighbourPosition.LEFT, 2);
        setValueInCell(cell, NeighbourPosition.RIGHT, 3);

    }

    private void setValueInCell(Cell cell, NeighbourPosition neighbourPosition, int arrayIndex) {
        if (this.value != null && this.value[arrayIndex]) {
            if (isVertical(neighbourPosition)) {
                cell.setLimitAt(createVerticalContainer(), neighbourPosition);
            } else {
                cell.setLimitAt(createHorizontalContainer(), neighbourPosition);
            }
        } else {
            cell.setLimitAt(createEmptyContainer(), neighbourPosition);
        }
    }

    private boolean isVertical(NeighbourPosition neighbourPosition) {
        return NeighbourPosition.RIGHT.equals(neighbourPosition) || NeighbourPosition.LEFT.equals(neighbourPosition);
    }

    private Container createEmptyContainer() {
        List<Content> lines = new ArrayList<>();
        Content theContent = new MutableContent(new NullValue(), "BORDER");
        lines.add(theContent);
        Container theContainer = new Container(new MutableContainer(lines));
        return theContainer;
    }

    private Container createVerticalContainer() {
        List<Content> lines = new ArrayList<>();
        Line theLine = new VerticalLine("VerticalLine");
        Content theContent = new MutableContent(theLine, "BORDER");
        lines.add(theContent);
        Container theContainer = new Container(new MutableContainer(lines));
        return theContainer;
    }

    private Container createHorizontalContainer() {
        List<Content> lines = new ArrayList<>();
        Line theLine = new HorizontalLine("HorizontalLine");
        Content theContent = new MutableContent(theLine, "BORDER");
        lines.add(theContent);
        Container theContainer = new Container(new MutableContainer(lines));
        return theContainer;
    }

}
