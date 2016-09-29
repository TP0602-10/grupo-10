package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameWonException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SudokuTest {

    private List<int[]> getMutableCellsOfSudoku(Grid grid) {
        List<List<Cell>> allCells = grid.getCells();

        List<int[]> mutableCellPosition = new ArrayList<int[]>();
        int row = 0;
        for (List<Cell> colList : allCells) {
            int col = 0;
            for (Cell cell : colList) {
                if (cell.isContentEditable()) {
                    int[] rowCol = {row,col};
                    mutableCellPosition.add( rowCol );
                }
                col++;
            }
            row++;
        }
        return mutableCellPosition;
    }

    @Test(expected = Test.None.class)
    public void createGridWithOneMutableCellAndDontWon() {
        Grid sudokuGrid = SudokuFactory.createGridFromScratch(79);

        Assert.assertFalse(sudokuGrid.isComplete());

        List<int[]> incompleteCellsPos = getMutableCellsOfSudoku(sudokuGrid);

        Assert.assertEquals(incompleteCellsPos.size(),2);

        int[] pos = incompleteCellsPos.get(0);
        sudokuGrid.getCellAt(pos[0],pos[1]).setValue(7);

        sudokuGrid.notifyGridUpdated();

    }

    @Ignore
    @Test(expected = GameWonException.class)
    public void createGridAndWin() {
        Grid sudokuGrid = SudokuFactory.createGridFromScratch(80);

        List<int[]> incompleteCellsPos = getMutableCellsOfSudoku(sudokuGrid);

        int[] pos = incompleteCellsPos.get(0);

        for (Integer number : ListHelper.createFromRange(1,9)) {
            sudokuGrid.getCellAt(pos[0],pos[1]).setValue(number);
            sudokuGrid.notifyGridUpdated();
        }
    }

}
