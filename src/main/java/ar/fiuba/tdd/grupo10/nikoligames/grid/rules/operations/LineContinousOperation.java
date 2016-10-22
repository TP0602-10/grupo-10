package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;


import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GridRuleWorkWithOnTagException;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.NeighbourType;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

/*
 * Rule operation that checks whether the contents of cells in the iterator form a contiguous line.
 * Also this rule implicitly checks if a cell is a neighbor of the next and previous in the iterator.
 * Incluided the first and the last.
 */
public class LineContinousOperation extends GridRuleOperation<Boolean> {
    public LineContinousOperation(List<String> contentTags) throws GridRuleWorkWithOnTagException {
        super(contentTags.get(0));
        throw new GridRuleWorkWithOnTagException("The rule only works with a content per cell");
    }

    public LineContinousOperation(String tag) {
        super(tag);
    }

    @Override
    public Boolean perform(GridRuleIterator iterator, Object... params) {
        if (!iterator.hasNext()) {
            return false;
        }
        Cell actualCell = (Cell)iterator.next();
        Cell firstCell = actualCell;
        Cell nextCell;
        while (iterator.hasNext()) {
            nextCell = (Cell)iterator.next();

            if (!areValid(actualCell,nextCell)) {
                return false;
            }
            actualCell = nextCell;
        }
        return areValid(actualCell,firstCell);
    }

    private String getTag(){
        return getContentTags().get(0);
    }

    private boolean areValid(Cell fromCell, Cell toCell) {
        if (fromCell != null && toCell != null) {
            NeighbourType neighbourType = fromCell.getNeighbourFrom(toCell);

            Content[] contents = {fromCell.getContent(), toCell.getContent() };
            if ( isApplicableOn(contents) ) {
                Line actualLine = (Line)fromCell.getContent(getTag()).getValueObject();
                Line nextLine = (Line)toCell.getContent(getTag()).getValueObject();
                if (neighbourType.isValid(actualLine,nextLine)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isApplicableOn(Content[] contents) {
        for (Content content : contents) {
            if (!isApplicableOn(content)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isApplicableOn(Container container) {
        return !container.getContent( getTag()).isEmpty();
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return ( !content.isEmpty() && content.getValue() instanceof Line );
    }

    @Override
    public String getOperationExplanation(Boolean result) {
        return "The operation check if a the contents of the cells generate a continuous line. The result is " + result.toString() + ".";
    }

}
