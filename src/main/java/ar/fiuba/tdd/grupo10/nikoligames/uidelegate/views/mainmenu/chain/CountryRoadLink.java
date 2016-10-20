package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;

import java.awt.*;
import javax.swing.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.UNAVAILABLE_GAME_ERROR_MSG;

class CountryRoadLink extends GameLink {

    CountryRoadLink(Component context) {
        super(context);
        this.gameEnum = GameEnum.COUNTRY_ROAD;
    }

    @Override
    void createGame(GameEnum game, String filePath) {
        showMessage(JOptionPane.INFORMATION_MESSAGE, UNAVAILABLE_GAME_ERROR_MSG);
    }

}
