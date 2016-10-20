package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.GamesBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.SudokuFactory;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GameController;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.SudokuController;

class SudokuLink extends GameLink {

    @Override
    public void execute(GameEnum gameEnum, String filePath) {
        if (GameEnum.SUDOKU.equals(gameEnum)) {
            try {
                //new SudokuController(SudokuFactory.createGridFromScratch(79));
                Grid grid = GamesBuilder.createUsingJson("src/main/java/ar/fiuba/tdd/grupo10/nikoligames/json/games/sudoku.json");
                new SudokuController(grid);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            next.execute(gameEnum, filePath);
        }
    }

}
