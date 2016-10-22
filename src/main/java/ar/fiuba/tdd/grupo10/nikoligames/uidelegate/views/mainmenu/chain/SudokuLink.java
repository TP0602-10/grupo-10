package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.GamesBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameBuilderErrorException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GameController;

import javax.swing.*;
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
            grid = GamesBuilder.createUsingJson(filePath);
            new GameController(grid, this.gameEnum);
        } catch (GameBuilderErrorException e) {
            showMessage(JOptionPane.ERROR_MESSAGE, ViewConstants.FILE_ERROR_MSG);
        }
    }

}
