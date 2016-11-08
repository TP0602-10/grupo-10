package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import org.apache.commons.lang3.StringUtils;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.LETTER;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.LINE;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.SELECT;

public class WordSearchValue implements PossibleValue {

    private Boolean value;

    public WordSearchValue(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public boolean isEquivalentTo(Object otherValue) {
        return (!value && (otherValue == null || StringUtils.isEmpty(String.valueOf(otherValue))))
                || (value && !StringUtils.isEmpty(String.valueOf(otherValue)));
    }

    @Override
    public void setValueInCell(Cell cell) {
        cell.setValue(value ? cell.getValue(LETTER) : "", SELECT);
    }

}
