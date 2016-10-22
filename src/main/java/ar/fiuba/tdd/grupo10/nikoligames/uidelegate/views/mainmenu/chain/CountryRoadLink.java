package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.CountryRoadFactory;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GameController;

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
        new GameController(new CountryRoadFactory().createGrid(), this.gameEnum);
    }

}
