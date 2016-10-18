package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for auxiliary uses of the GridRuleIterator@{@link GridRuleIterator}.
 */
public class GridRuleIteratorHelper {

    public static List<Container> listAllCells(GridRuleIterator iterator) {
        List<Container> allContainers = new ArrayList<>();
        while (iterator.hasNext()) {
            Container container = iterator.next();
            allContainers.add(container);
        }
        iterator.restart();
        return allContainers;
    }

}
