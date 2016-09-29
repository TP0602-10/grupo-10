package ar.fiuba.tdd.grupo10.nikoligames;



import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

import java.util.List;


//2016
public class Main {
    private static void printCells(List<List<Cell>> allCells) throws NoFindContentbyTagException {
        for (List<Cell> rowCells : allCells) {
            for (Cell cell : rowCells) {
                if ( cell.getValue() != null ) {
                    System.out.print(cell.getValue() + " ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws NoFindContentbyTagException {
        System.out.println("This is just a template project");
        Grid sudokuGrid;
        try {
            sudokuGrid = SudokuFactory.createFromScratch(81);
        } catch (WrongNumberOfGridCellsException e) {
            sudokuGrid = null;
            e.printStackTrace();
            System.exit(1);
        }

        printCells( sudokuGrid.getCells() );


    }


}
