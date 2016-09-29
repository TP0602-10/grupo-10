package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameWonException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.RandomHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void createGridWithOneMutableCell() {
        Grid sudokuGrid = SudokuFactory.createFromScratch(80);

        Assert.assertFalse(sudokuGrid.isComplete());

        List<int[]> incompleteCellsPos = getMutableCellsOfSudoku(sudokuGrid);

        Assert.assertEquals(incompleteCellsPos.size(),1);

    }

    @Test(expected = GameWonException.class)
    public void createGridAndWin() {
        Grid sudokuGrid = SudokuFactory.createFromScratch(80);

        List<int[]> incompleteCellsPos = getMutableCellsOfSudoku(sudokuGrid);

        int[] pos = incompleteCellsPos.get(0);

        for (Integer number : ListHelper.createFromRange(1,9)) {
            sudokuGrid.getCellAt(pos[0],pos[1]).setValue(number);
            sudokuGrid.notifyGridUpdated();
        }
    }

}
