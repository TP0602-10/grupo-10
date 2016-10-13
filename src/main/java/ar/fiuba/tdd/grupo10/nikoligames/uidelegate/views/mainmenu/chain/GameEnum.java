package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

public enum GameEnum {

    SUDOKU("Sudoku"),
    KAKURO("Kakuro");

    private String description;

    GameEnum(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
