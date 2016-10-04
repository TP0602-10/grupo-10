package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

abstract class GameLink {

    GameLink next;

    public void setNext(GameLink next) {
        this.next = next;
    }

    public abstract void execute(GameEnum game);

}
