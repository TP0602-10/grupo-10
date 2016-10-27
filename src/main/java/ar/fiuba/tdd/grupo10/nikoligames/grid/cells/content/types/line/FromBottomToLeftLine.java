package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line;

public class FromBottomToLeftLine extends Line {

    public FromBottomToLeftLine(String name) {
        super(name);
    }

    @Override
    public boolean isValidOnLeft() {
        return true;
    }

    @Override
    public boolean isValidOnBottom() {
        return true;
    }

}
