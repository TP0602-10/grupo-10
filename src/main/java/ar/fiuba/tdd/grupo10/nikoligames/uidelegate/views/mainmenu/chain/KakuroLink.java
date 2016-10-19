package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.KakuroFactory;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GameController;

class KakuroLink extends GameLink {

    @Override
    public void execute(GameEnum gameEnum, String filePath) {
        if (GameEnum.KAKURO.equals(gameEnum)) {
            try {
                new GameController(KakuroFactory.createGrid(2), gameEnum);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            next.execute(gameEnum, filePath);
        }
    }

}
