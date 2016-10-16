package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.KakuroFactory;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GameController;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.KakuroController;

class KakuroLink extends GameLink {

    @Override
    public void execute(GameEnum gameEnum) {
        if (GameEnum.KAKURO.equals(gameEnum)) {
            try {
                new KakuroController(KakuroFactory.createGrid(2));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            next.execute(gameEnum);
        }
    }

}
