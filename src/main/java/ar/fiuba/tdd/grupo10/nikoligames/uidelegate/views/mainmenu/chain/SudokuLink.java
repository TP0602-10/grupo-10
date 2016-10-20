package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.SudokuFactory;
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
        new GameController(SudokuFactory.createGridFromScratch(79), gameEnum);
    }

}
