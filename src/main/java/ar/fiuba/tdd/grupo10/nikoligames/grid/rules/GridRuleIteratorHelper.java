package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for auxiliary uses of the GridRuleIterator@{@link GridRuleIterator}.
 */
public class GridRuleIteratorHelper {

    public static List<Cell> listAllCells(GridRuleIterator iterator) {
        List<Cell> allCells = new ArrayList<>();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            allCells.add(cell);
        }
        iterator.restart();
        return allCells;
    }

}
