package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

public interface PossibleValue {

    Object getValue();

    boolean isEquivalentTo(Object otherValue);

    void setValueInCell(Cell cell);

}
