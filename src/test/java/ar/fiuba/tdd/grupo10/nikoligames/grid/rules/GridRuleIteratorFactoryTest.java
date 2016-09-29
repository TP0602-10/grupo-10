package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;


import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    This test the ItertorFactory and the Iterator.
 */
public class GridRuleIteratorFactoryTest {

    private List<List<Cell>> theGridToTest;
    private static final Integer COLS = 4;
    private static final Integer ROWS = 4;

    @Before
    public void setUp() {
        /*
         ********************
         *  0   1   2   3   *
         *  4   5   6   7   *
         *  8   9   10  11  *
         *  12  13  14  15  *
         ********************
        */
        List<Cell> allCells = new ArrayList<>();
        for (int i = 0; i < ROWS * COLS; i++) {
            allCells.add( new MutableCell(
                    new MutableContent<>(i,"tag")
            ));
        }
        theGridToTest = ListHelper.buildMatrixFromFlattenList(allCells,ROWS,COLS);
    }

    @Test
    public void generateIteratorFirstRow() {

        GridRuleIterator firstRow = GridRuleIteratorFactory.iteratorForRow(theGridToTest,0);

        int[] expectedFirstRow = {0,1,2,3};

        int index = 0;
        while ( firstRow.hasNext() ) {
            Cell theCell = firstRow.next();
            Assert.assertEquals(expectedFirstRow[index],(int)theCell.getValue());
            index++;
        }

    }

    @Test
    public void iterateSecondColumn() {
        GridRuleIterator secondColumnIterator = GridRuleIteratorFactory.iteratorForColumn(theGridToTest,1);

        int[] expectedSecondColumn = {1,5,9,13};

        int index = 0;
        while ( secondColumnIterator.hasNext() ) {
            Cell theCell = secondColumnIterator.next();
            Assert.assertEquals(expectedSecondColumn[index],(int)theCell.getValue());
            index++;
        }
    }

    @Test
    public void generateIteratorAllCols() {

        List<GridRuleIterator> colsIterators = GridRuleIteratorFactory.iteratorsForAllColumns(theGridToTest);

        int[][] expectedCols = {
                {0,4,8,12},
                {1,5,9,13},
                {2,6,10,14},
                {3,7,11,15}
        };

        int colIndex = 0;
        for (GridRuleIterator iterator : colsIterators) {
            int index = 0;
            int[] expectedCol = expectedCols[colIndex];
            while ( iterator.hasNext() ) {
                Cell theCell = iterator.next();
                Assert.assertEquals(expectedCol[index],(int)theCell.getValue());
                index++;
            }
            colIndex++;
        }

    }

    @Test
    public void iterateAllCellBlocks() {
        List<GridRuleIterator> blocksIterator = GridRuleIteratorFactory.iteratorsForAllCellBlocks(
                theGridToTest,
                2,
                2
        );

        Assert.assertEquals(blocksIterator.size(),4);

        int[][] expectedBlocks = {
                {0,1,4,5},
                {2,3,6,7},
                {8,9,12,13},
                {10,11,14,15}
        };
        int blockIndex = 0;
        for (GridRuleIterator iterator : blocksIterator) {
            int[] expectedBlock = expectedBlocks[blockIndex];
            int index = 0;
            while (iterator.hasNext()) {
                Cell theCell = iterator.next();
                Assert.assertEquals(expectedBlock[index],(int)theCell.getValue());
                index++;
            }
            blockIndex++;
        }

    }

    @Test
    public void iterateCustomColumn() {
        GridRuleIterator columnIterator = GridRuleIteratorFactory.iteratorForCustomColumn(
                theGridToTest,
                2,
                1,
                2
        );

        int[] expectedCustomCol = {6,10};
        int index = 0;
        while (columnIterator.hasNext()) {
            Cell theCell = columnIterator.next();
            Assert.assertEquals(expectedCustomCol[index],(int)theCell.getValue());
            index++;
        }
    }

    @Test
    public void iterateCustomRow() {
        GridRuleIterator rowIterator = GridRuleIteratorFactory.iteratorForCustomRow(
                theGridToTest,
                1,
                1,
                3
        );

        int[] expectedCustomRow = {5,6,7};
        int index = 0;
        while (rowIterator.hasNext()) {
            Cell theCell = rowIterator.next();
            Assert.assertEquals(expectedCustomRow[index],(int)theCell.getValue());
            index++;
        }
    }

}
