package ar.fiuba.tdd.grupo10.nikoligames;



import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

import java.util.List;


//2016
public class Main {
    private static void printCells(List<List<Cell>> allCells) {
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

    public static void main(String[] args) {
        System.out.println("This is just a template project");
        Grid sudokuGrid;
        try {
            sudokuGrid = SudokuFactory.createFromScratch(15);
        } catch (WrongNumberOfGridCellsException e) {
            sudokuGrid = null;
            e.printStackTrace();
            System.exit(1);
        }

        printCells( sudokuGrid.getCells() );


    }


}
