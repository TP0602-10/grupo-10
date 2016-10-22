package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.GamesBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameBuilderErrorException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GameController;

import java.awt.*;

class SudokuLink extends GameLink {

    SudokuLink(Component context) {
        super(context);
        this.gameEnum = GameEnum.SUDOKU;
    }

    @Override
    void createGame(GameEnum game, String filePath) {
        Grid grid;
        try {
            grid = GamesBuilder.createUsingJson("src/main/java/ar/fiuba/tdd/grupo10/nikoligames/json/games/sudoku.json");
            new GameController(grid, this.gameEnum);
        } catch (GameBuilderErrorException e) {
            e.printStackTrace();
        }

    }

}
