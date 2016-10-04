package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu;

import ar.fiuba.tdd.grupo10.nikoligames.KakuroFactory;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridController;

class KakuroLink extends GameLink {

    @Override
    public void execute(GameEnum gameEnum) {
        if (GameEnum.KAKURO.equals(gameEnum)) {
            try {
                new GridController(KakuroFactory.createGrid(2));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            next.execute(gameEnum);
        }
    }

}
