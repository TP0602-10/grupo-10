package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;

import java.awt.*;

public class GamesChain {

    private GameLink firstLink;

    public GamesChain(Component context) {
        firstLink = new SudokuLink(context);
        GameLink kakuroLink = new KakuroLink(context);
        firstLink.setNext(kakuroLink);
        GameLink countryRoadLink = new CountryRoadLink(context);
        kakuroLink.setNext(countryRoadLink);
        GameLink gokigenNanameLink = new GokigenNanameLink(context);
        countryRoadLink.setNext(gokigenNanameLink);
        GameLink slitherlinkLink = new SlitherlinkLink(context);
        gokigenNanameLink.setNext(slitherlinkLink);
        GameLink unavailableGameLink = new UnavailableGameLink(context);
        slitherlinkLink.setNext(unavailableGameLink);
    }

    public void execute(GameEnum gameEnum, String filePath) {
        firstLink.execute(gameEnum, filePath);
    }

}
