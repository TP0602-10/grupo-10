package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;


import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GridRuleWorkWithOnTagException;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.NeighbourType;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

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
        Cell actualCell = (Cell)iterator.next();
        while (iterator.hasNext()) {
            Cell nextCell = (Cell)iterator.next();
            NeighbourType neighbourType = actualCell.getNeighbourFrom(nextCell);

            Content[] contents = {actualCell.getContent(), nextCell.getContent() };
            if (!isApplicableOn(contents)) {
                return false;
            }

            Line actualLine = (Line)actualCell.getValue();
            Line nextLine = (Line)nextCell.getValue();
            if (!neighbourType.isValid(actualLine,nextLine)) {
                return false;
            }

            actualCell = nextCell;
        }
        return true;
    }

    public boolean isApplicableOn(Content[] contents) {
        for (Content content : contents) {
            if (!isApplicableOn(content)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isApplicableOn(Container container) {
        return !container.getContent( getContentTags().get(0) ).isEmpty();
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
