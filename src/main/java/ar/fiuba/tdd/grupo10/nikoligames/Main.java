package ar.fiuba.tdd.grupo10.nikoligames;



import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

import java.util.List;


//2016
public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a template project");

        Grid sudokuGrid = SudokuFactory.createFromScratch( 15 );
        List<List<Cell>> allCells = sudokuGrid.getCells();
        for (List<Cell> rowCells : allCells) {
            for (Cell cell : rowCells) {
                if ( cell.getContent("tag").getValue() != null ) {
                    System.out.print(cell.getContent("tag").getValue() + " ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }

    }


}
