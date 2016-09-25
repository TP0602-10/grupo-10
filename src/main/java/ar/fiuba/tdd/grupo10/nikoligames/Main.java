package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.mvc.model.GridModel;
import ar.fiuba.tdd.grupo10.nikoligames.mvc.views.NikoliView;

public class Main {
    public static void main(String[] args) {
        Grid grid = SudokuFactory.createFromScratch(70);
        GridModel gridModel = new GridModel(grid);
        new NikoliView(gridModel);
    }


}
