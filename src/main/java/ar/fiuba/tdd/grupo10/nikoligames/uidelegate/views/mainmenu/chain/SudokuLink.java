package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;

import java.awt.*;

class SudokuLink extends GameLink {

    SudokuLink(Component context) {
        super(context);
        this.gameEnum = GameEnum.SUDOKU;
    }
}
