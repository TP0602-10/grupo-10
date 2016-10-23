package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line;

public class FromTopToRightLine extends Line {

    public FromTopToRightLine(String name) {
        super(name);
    }

    @Override
    public boolean isValidOnTop() {
        return true;
    }

    @Override
    public boolean isValidOnRight() {
        return true;
    }

}
