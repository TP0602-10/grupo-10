package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

public abstract class GridCell<T extends CellContent> {
    private CellState cellState;
    private T content;

    public GridCell(CellState cellState) {
        this.cellState = cellState;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public abstract boolean isContentEditable();

    public abstract boolean areRulesApplicable();
}
