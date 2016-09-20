package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for auxiliary uses of the GridRuleIterator@{@link GridRuleIterator}.
 */
public class GridRuleIteratorHelper {

    public static List<GridCell> listAllCells(GridRuleIterator iterator) {
        List<GridCell> allCells = new ArrayList<>();
        while (iterator.hasNext()) {
            GridCell cell = iterator.next();
            allCells.add(cell);
        }
        return allCells;
    }

}
