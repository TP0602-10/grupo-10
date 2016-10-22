package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line;

public class FromTopToLeftLine extends Line {

    public FromTopToLeftLine(String name) {
        super(name);
    }

    @Override
    public boolean isValidOnTop() {
        return true;
    }

    @Override
    public boolean isValidOnLeft() {
        return true;
    }

}
