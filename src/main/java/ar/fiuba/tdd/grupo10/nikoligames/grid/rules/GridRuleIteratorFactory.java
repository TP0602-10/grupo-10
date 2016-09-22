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

    public static GridRuleIterator iteratorForRow(List<List<GridCell>> grid, int rowNumber) {
        return new GridRuleIterator(grid.get(rowNumber), "Iterate over the iteratorForRow " + rowNumber);
    }

    public static GridRuleIterator iteratorForColumn(List<List<GridCell>> grid, int columnNumber) {
        List<GridCell> column = new ArrayList<>();
        grid.forEach(row -> column.add(row.get(columnNumber)));
        return new GridRuleIterator(column, "Iterate over the iteratorForColumn " + columnNumber);
    }

    private static int getNumberOfRows(List<List<GridCell>> grid) {
        return grid.size();
    }

    private static int getNumberOfColumns(List<List<GridCell>> grid) {
        // Symmetric grid, all rows must have the same size.
        return grid.get(0).size();
    }
}
