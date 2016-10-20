package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line;

public class FromBottomLeftToTopRightDiagonal extends Line {
    @Override
    public boolean isValidOnTopRight() {
        return true;
    }

    @Override
    public boolean isValidOnBottomLeft() {
        return true;
    }
}
