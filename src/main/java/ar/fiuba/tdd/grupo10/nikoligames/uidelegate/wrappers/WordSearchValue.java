package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import org.apache.commons.lang3.StringUtils;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.LINE;

public class WordSearchValue implements PossibleValue {

    private Boolean value;

    public WordSearchValue(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public boolean isEquivalentTo(Object otherValue) {
        if (!value && (otherValue == null || StringUtils.isEmpty(String.valueOf(otherValue)))) {
            return true;
        } else if (value && !StringUtils.isEmpty(String.valueOf(otherValue))) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueInCell(Cell cell) {
        if (value) {
            cell.setValue(cell.getValue("letter"), "select");
        } else {
            cell.setValue("", "select");
        }
    }

}
