package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating common grid iterators such as row or column iterators.
 */
public class GridRuleIteratorFactory {

    public static List<GridRuleIterator> iteratorsForAllRows(List<List<GridCell>> grid) {
        List<GridRuleIterator> allRows = new ArrayList<>();
        for (int r = 0; r < getNumberOfRows(grid); r++) {
            allRows.add(iteratorForRow(grid, r));
        }
        return allRows;
    }

    public static List<GridRuleIterator> iteratorsForAllColumns(List<List<GridCell>> grid) {
        List<GridRuleIterator> allColumns = new ArrayList<>();
        for (int c = 0; c < getNumberOfColumns(grid); c++) {
            allColumns.add(iteratorForColumn(grid, c));
        }
        return allColumns;
    }

    public static List<GridRuleIterator> iteratorsForAllCellBlocks(List<List<GridCell>> grid, int rowDivisions, int columnsDivisions) {
        List<GridRuleIterator> allCellBlocks = new ArrayList<>();
        int rowBlockSize = getRowBlockSize(grid, rowDivisions);
        int columnBlockSize = getColumnBlockSize(grid, columnsDivisions);
        for (int r = 0; r < getNumberOfRows(grid); r += rowBlockSize) {
            for (int c = 0; c < getNumberOfColumns(grid); c += columnBlockSize) {
                allCellBlocks.add(iteratorForCellBlock(grid, r, c, rowBlockSize - 1, columnBlockSize - 1));
            }
        }
        return allCellBlocks;
    }

    public static GridRuleIterator iteratorForRow(List<List<GridCell>> grid, int rowNumber) {
        return new GridRuleIterator(grid.get(rowNumber), "Iterate over the row " + rowNumber);
    }

    public static GridRuleIterator iteratorForColumn(List<List<GridCell>> grid, int columnNumber) {
        List<GridCell> column = new ArrayList<>();
        grid.forEach(row -> column.add(row.get(columnNumber)));
        return new GridRuleIterator(column, "Iterate over the column " + columnNumber);
    }

    public static GridRuleIterator iteratorForCellBlock(List<List<GridCell>> grid, int startRow, int startColumn, int endRow, int endColumn) {
        List<GridCell> cellBlock = new ArrayList<>();
        for (int r = startRow; r < endRow; r++) {
            for (int c = startColumn; c < endColumn; c++) {
                cellBlock.add(grid.get(r).get(c));
            }
        }
        return new GridRuleIterator(cellBlock, "Iterate over the cell block started in ["
                + startRow + "," + startColumn + "] and ended in ["
                + endRow + "," + endColumn + "]");
    }

    private static int getNumberOfRows(List<List<GridCell>> grid) {
        return grid.size();
    }

    private static int getNumberOfColumns(List<List<GridCell>> grid) {
        // All rows must have the same size.
        return grid.get(0).size();
    }

    private static int getRowBlockSize(List<List<GridCell>> grid, int rowDivisions) {
        return grid.size() / rowDivisions;
    }

    private static int getColumnBlockSize(List<List<GridCell>> grid, int columnsDivisions) {
        // All rows must have the same size.
        return grid.get(0).size() / columnsDivisions;
    }
}
