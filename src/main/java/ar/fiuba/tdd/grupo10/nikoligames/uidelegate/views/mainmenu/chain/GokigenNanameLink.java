package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;

import javax.swing.*;
import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.UNAVAILABLE_GAME_ERROR_MSG;

class GokigenNanameLink extends GameLink {

    GokigenNanameLink(Component context) {
        super(context);
        this.gameEnum = GameEnum.GOKIGEN_NANAME;
    }

    @Override
    void createGame(GameEnum game, String filePath) {
        showMessage(JOptionPane.INFORMATION_MESSAGE, UNAVAILABLE_GAME_ERROR_MSG);
    }

}
