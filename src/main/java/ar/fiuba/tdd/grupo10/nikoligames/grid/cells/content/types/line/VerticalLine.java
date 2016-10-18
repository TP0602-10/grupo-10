package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line;

public class VerticalLine extends Line {

    @Override
    public boolean isValidOnTop() {
        return true;
    }

    @Override
    public boolean isValidOnBottom() {
        return true;
    }

}
