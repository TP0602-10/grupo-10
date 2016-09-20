package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

/**
 * Cell whose content can be changed.
 */
public class MutableCell extends GridCell {

    public MutableCell(CellState cellState) {
        super(cellState);
    }

    @Override
    public boolean isContentEditable() {
        return true;
    }

}
