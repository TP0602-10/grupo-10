package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line;

public class HorizontalLine extends Line {

    public HorizontalLine(String name) {
        super(name);
    }

    @Override
    public boolean isValidOnLeft() {
        return true;
    }

    @Override
    public boolean isValidOnRight() {
        return true;
    }

}
