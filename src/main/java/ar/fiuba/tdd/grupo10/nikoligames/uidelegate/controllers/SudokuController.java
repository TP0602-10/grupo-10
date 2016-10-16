package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters.GridAdapter;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters.SudokuGridAdapter;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.GameView;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.GridView;

public class SudokuController extends GameController {

    public SudokuController(Grid grid) {
        super(grid);
        GridAdapter adapter = new SudokuGridAdapter(grid);
        adapter.addTableModelListener(this);
        setView(new GameView(new GridView(adapter, GameEnum.SUDOKU)));
    }

}
