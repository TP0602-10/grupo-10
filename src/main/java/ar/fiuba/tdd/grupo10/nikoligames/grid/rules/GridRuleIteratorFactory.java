package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating common grid iterators such as row or column iterators.
 */
public final class GridRuleIteratorFactory {

    public static List<GridRuleIterator> iteratorsForAllRows(List<List<Container>> grid) {
        List<GridRuleIterator> allRows = new ArrayList<>();
        for (int r = 0; r < getNumberOfRows(grid); r++) {
            allRows.add(iteratorForRow(grid, r));
        }
        return allRows;
    }

    public static List<GridRuleIterator> iteratorsForAllColumns(List<List<Container>> grid) {
        List<GridRuleIterator> allColumns = new ArrayList<>();
        for (int c = 0; c < getNumberOfColumns(grid); c++) {
            allColumns.add(iteratorForColumn(grid, c));
        }
        return allColumns;
    }

    public static List<GridRuleIterator> iteratorsForAllCellBlocks(List<List<Container>> grid, int rowDivisions, int columnsDivisions) {
        List<GridRuleIterator> allCellBlocks = new ArrayList<>();
        int rowBlockSize = getRowBlockSize(grid, rowDivisions);
        int columnBlockSize = getColumnBlockSize(grid, columnsDivisions);
        for (int r = 0; r < getNumberOfRows(grid); r += rowBlockSize) {
            for (int c = 0; c < getNumberOfColumns(grid); c += columnBlockSize) {
                allCellBlocks.add(iteratorForCellBlock(grid, r, c, r + rowBlockSize - 1, c + columnBlockSize - 1));
            }
        }
        return allCellBlocks;
    }

    static GridRuleIterator iteratorForRow(List<List<Container>> grid, int rowNumber) {
        return new GridRuleIterator(grid.get(rowNumber), "Iterate over the row " + rowNumber + ".");
    }

    private static List<Container> getColumn(List<List<Container>> grid, int columnNumber) {
        List<Container> column = new ArrayList<>();
        grid.forEach(row -> column.add(row.get(columnNumber)));
        return column;
    }

    public static GridRuleIterator iteratorForColumn(List<List<Container>> grid, int columnNumber) {
        return new GridRuleIterator(
                getColumn(grid, columnNumber),
                "Iterate over the column " + columnNumber + "."
        );
    }

    public static GridRuleIterator iteratorForCustomColumn(List<List<Container>> grid, int columnNumber, int startIndex, int endIndex) {
        List<Container> customColumn = getColumn(grid, columnNumber).subList(startIndex, endIndex + 1);
        return new GridRuleIterator(customColumn,
                "Iterate over the column " + columnNumber + ". From position "
                        + (startIndex - 1) + " to position " + (endIndex + 1) + "."
        );
    }

    public static GridRuleIterator iteratorForCustomRow(List<List<Container>> grid, int rowNumber, int startIndex, int endIndex) {
        List<Container> customRowToBeIterated = grid.get(rowNumber).subList(startIndex, endIndex + 1);
        return new GridRuleIterator(customRowToBeIterated, "Iterate over row " + rowNumber + ". From position "
                + (startIndex + 1) + " to position " + (endIndex + 1) + ".");
    }

    public static GridRuleIterator iteratorForCellBlock(List<List<Container>> grid, int startRow,
                                                        int startColumn, int endRow, int endColumn) {
        List<Container> cellBlock = new ArrayList<>();
        for (int r = startRow; r <= endRow; r++) {
            for (int c = startColumn; c <= endColumn; c++) {
                cellBlock.add(grid.get(r).get(c));
            }
        }
        return new GridRuleIterator(cellBlock, "Iterate over the cell block started in ["
                + startRow + "," + startColumn + "] and ended in ["
                + endRow + "," + endColumn + "].");
    }

    private static int getNumberOfRows(List<List<Container>> grid) {
        return grid.size();
    }

    private static int getNumberOfColumns(List<List<Container>> grid) {
        // All rows must have the same size.
        return grid.get(0).size();
    }

    private static int getRowBlockSize(List<List<Container>> grid, int rowDivisions) {
        return grid.size() / rowDivisions;
    }

    private static int getColumnBlockSize(List<List<Container>> grid, int columnsDivisions) {
        // All rows must have the same size.
        return grid.get(0).size() / columnsDivisions;
    }
}
