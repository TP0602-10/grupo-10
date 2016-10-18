package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

public class SumLinesPointingToSharedCornerOperation extends GridRuleOperation<Integer> {

    public SumLinesPointingToSharedCornerOperation(List<String> contentTags) {
        super(contentTags);
    }

    public SumLinesPointingToSharedCornerOperation(String tag) {
        super(tag);
    }

    @Override
    public Integer perform(GridRuleIterator iterator, Object... params) {
        // TODO: 18/10/16 Think how to implement this!
        return null;
    }

    @Override
    public boolean isApplicableOn(Container container) {
        return !container.getContent( getContentTags().get(0) ).isEmpty();
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return ( !content.isEmpty() && content.getValue() instanceof Line);
    }

    @Override
    public String getOperationExplanation(Integer result) {
        return "The operation sums all the line contents of the iterator cells pointing to a corner shared by all."
                + " The result is " + result.toString() + ".";
    }
}
