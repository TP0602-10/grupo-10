package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;

import java.util.Iterator;
import java.util.List;

/**
 * Iterator that contains all the cells of matter for the rule that use it.
 */
public class GridRuleIterator implements Iterator {
    private static final int INIT_INDEX = -1;

    private final List<Container> containers;
    private final String explanation;
    private int actualIndex = INIT_INDEX;

    public GridRuleIterator(List<Container> containers, String explanation) {
        this.containers = containers;
        this.explanation = explanation;
    }

    @Override
    public boolean hasNext() {
        try {
            containers.get(actualIndex + 1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public Container next() {
        actualIndex++;
        return containers.get(actualIndex);
    }

    public void restart() {
        actualIndex = INIT_INDEX;
    }

    public String getCellsInvolvedExplanation() {
        return this.explanation;
    }
}
