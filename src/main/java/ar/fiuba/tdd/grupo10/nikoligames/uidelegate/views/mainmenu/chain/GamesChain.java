package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

public class GamesChain {

    private GameLink firstLink;

    public GamesChain() {
        firstLink = new SudokuLink();
        GameLink kakuroLink = new KakuroLink();
        firstLink.setNext(kakuroLink);
        GameLink unavailableGameLink = new UnavailableGameLink();
        kakuroLink.setNext(unavailableGameLink);
    }

    public void execute(GameEnum gameEnum) {
        firstLink.execute(gameEnum);
    }

}
