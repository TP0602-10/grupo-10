package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;


import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.NeighbourType;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * This rule checks if all Cell elements of the iterator form a line circuit.
 * Concrete children must know which line orientations are valid to form a circuit.
 * Only Work with Cells and one tag per Cell.
 */
public abstract class LineCircuitOperation extends GridRuleOperation<Boolean> {
    private String onlyTag;

    public LineCircuitOperation( String tag ) {
        super(tag);
        onlyTag = tag;
    }

    protected abstract NeighbourPosition[] getAvailablePositions();

    @Override
    public Boolean perform(GridRuleIterator iterator, Object... params) {
        List<Cell> cellsInIterator = new ArrayList<>();
        while ( iterator.hasNext() ) {
            Container theContainter = iterator.next();
            if (isApplicableOn(theContainter)) {
                cellsInIterator.add( (Cell)theContainter );
            }
        }
        return cellsInIterator.size() > 0 ? deepCircuitSearch( cellsInIterator ) : false;
    }

    private String getTag() {
        return onlyTag;
    }

    private boolean formContinousLine(Cell fromCell, Cell toCell, NeighbourType neighbourType) {
        Line fromLine = (Line) fromCell.getValue(getTag());
        Line toLine;
        try {
            toLine = (Line) toCell.getValue( getTag() );
        } catch (NoFindContentbyTagException e) {
            return false;
        }
        return neighbourType.isValid(fromLine, toLine);
    }

    private boolean canAdvance(Cell fromCell, NeighbourContainer toNeighbour) {
        if (toNeighbour != null) {
            Cell neighbourCell = (Cell)toNeighbour.getNeighbourContainer();
            if (isApplicableOn(neighbourCell)) {
                return formContinousLine( fromCell, neighbourCell, toNeighbour.getNeighbourType() );
            }
        }
        return false;
    }

    private Cell searchNeighbourCellToContinue(Cell fromCell, Cell previous) {
        List<NeighbourContainer> neighboursOfCell = fromCell.getNeighbours(getAvailablePositions());
        for (NeighbourContainer neighbour : neighboursOfCell) {
            if ( neighbour != null ) {
                Cell neighbourCell = (Cell) neighbour.getNeighbourContainer();
                if (neighbourCell != previous && canAdvance(fromCell, neighbour)) {
                    return neighbourCell;
                }
            }
        }
        return null;
    }

    private boolean continueSearch(Cell first, Cell next) {
        return (next != first && next != null);
    }

    private Boolean deepCircuitSearch( List<Cell> cells ) {
        Cell firstAplicable = cells.remove(0);
        Cell actual = firstAplicable;
        Cell previous = null;
        Cell next = null;

        boolean doDeepSearch = (cells.size() > 0);

        while (doDeepSearch) {
            next = searchNeighbourCellToContinue( actual, previous );
            doDeepSearch = ( continueSearch(firstAplicable, next) && cells.contains(next) );
            previous = actual;
            actual = next;
        }
        return (next != null);
    }

    @Override
    public boolean isApplicableOn(Container container) {
        return ( container instanceof Cell && isApplicableOn( container.getContent( getTag() ) ) );
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return content.getValue() instanceof Line;
    }

    @Override
    public String getOperationExplanation(Boolean result) {
        return "The operation check if the selected tag content of the applicable Cells in the iterator generate"
                + " a close circuit. The result is " + result.toString() + ".";
    }
}
