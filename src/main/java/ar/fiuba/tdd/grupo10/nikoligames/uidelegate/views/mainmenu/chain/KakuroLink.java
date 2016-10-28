package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;

import java.awt.*;

class KakuroLink extends GameLink {

    KakuroLink(Component context) {
        super(context);
        this.gameEnum = GameEnum.KAKURO;
    }
}
