package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import java.awt.*;

public class GamesChain {

    private GameLink firstLink;

    public GamesChain(Component context) {
        firstLink = new SudokuLink();
        GameLink kakuroLink = new KakuroLink();
        firstLink.setNext(kakuroLink);
        GameLink unavailableGameLink = new UnavailableGameLink(context);
        kakuroLink.setNext(unavailableGameLink);
    }

    public void execute(GameEnum gameEnum) {
        firstLink.execute(gameEnum);
    }

}
