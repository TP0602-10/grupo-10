package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

/**
 * State of an empty cell.
 * When content is setted, the cell state changes to Filled{@link FilledState}.
 */
public class EmptyState implements CellState {

    @Override
    public CellContent getContent() {
        return null;
    }

    @Override
    public void setContent(CellContent content, GridCell cell) {
        CellState newState = new FilledState(content);
        cell.setState(newState);
    }

    @Override
    public void clearContent(GridCell cell) {}

    @Override
    public boolean areRulesApplicable() {
        return false;
    }
}
