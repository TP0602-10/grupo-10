package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.SudokuController;

public class Main {
    public static void main(String[] args) {
        new SudokuController(SudokuFactory.createFromScratch(70));
    }


}
