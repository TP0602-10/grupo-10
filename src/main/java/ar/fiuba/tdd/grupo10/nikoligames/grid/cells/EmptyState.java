package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

public class EmptyState implements CellState {

    @Override
    public CellContent getContent() {
        // TODO: 18/09/16 Check how to solve this without returning null. We could extend CellContent
        // and implement NullContent, but I can't figure out how the class would be.
        return null;
    }

    @Override
    public void setContent(CellContent content, GridCell cell) {
        CellState newState = new FilledState(content);
        cell.setState(newState);
    }

    @Override
    public void clearContent(GridCell cell) {}
}
