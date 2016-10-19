package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

public class SudokuGridAdapter extends GridAdapter {

    public SudokuGridAdapter(Grid grid) {
        super(grid);
    }

    @Override
    protected String getGridValueRegex() {
        return "^[1-9]";
    }

}
