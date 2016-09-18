package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

public class ImmutableCell extends GridCell {

    public ImmutableCell(CellState cellState) {
        super(cellState);
    }

    @Override
    public boolean isContentEditable() {
        return false;
    }

    @Override
    public boolean areRulesApplicable() {
        return true;
    }
}
