package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

public abstract class GridCell {
    private CellState cellState;

    public GridCell(CellState cellState) {
        this.cellState = cellState;
    }

    public void setState(CellState state) {
        this.cellState = state;
    }

    public void setContent(CellContent content) {
        cellState.setContent(content, this);
    }

    public CellContent getContent() {
        return cellState.getContent();
    }

    public void clearContent() {
        cellState.clearContent(this);
    }

    public abstract boolean isContentEditable();

    public abstract boolean areRulesApplicable();
}
