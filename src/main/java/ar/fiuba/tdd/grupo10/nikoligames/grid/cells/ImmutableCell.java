package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

/**
 * Cell whose content cannot be changed.
 */
public class ImmutableCell extends GridCell {

    public ImmutableCell(CellState cellState) {
        super(cellState);
    }

    @Override
    public boolean isContentEditable() {
        return false;
    }
}
