package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import javax.swing.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.*;

class UnavailableGameLink extends GameLink {

    UnavailableGameLink(Component context) {
        super(context);
    }

    @Override
    void execute(GameEnum gameEnum, String filePath) {
        createGame(gameEnum, filePath);
    }

    @Override
    void createGame(GameEnum game, String filePath) {
        showMessage(JOptionPane.INFORMATION_MESSAGE, UNAVAILABLE_GAME_ERROR_MSG);
    }

}
