package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;

abstract class GameLink {

    protected GameLink next;

    public void setNext(GameLink next) {
        this.next = next;
    }

    public abstract void execute(GameEnum game);

}
