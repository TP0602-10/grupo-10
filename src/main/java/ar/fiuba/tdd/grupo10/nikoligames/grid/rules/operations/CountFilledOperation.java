package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

/**
 * This rule count the filed containers in the iterator.
 */
public class CountFilledOperation extends GridRuleOperation<Integer> {

    public CountFilledOperation(String tag ) {
        super(tag);
    }

    public CountFilledOperation(List<String> tags ) {
        super(tags);
    }

    @Override
    public Integer perform(GridRuleIterator iterator, Object... params) {
        Integer count = 0;

        while (iterator.hasNext()) {
            Container container = iterator.next();
            if ( isApplicableOn(container) ) {
                count++;
            }
        }

        return count;
    }

    @Override
    public boolean isApplicableOn(Container container) {
        Content content;
        try {
            content = container.getContent( getContentTags().get(0) );
        } catch (NoFindContentbyTagException e) {
            return false;
        }
        return isApplicableOn(content);
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return ((content != null) && !content.isEmpty());
    }

    @Override
    public String getOperationExplanation(Integer result) {
        return null;
    }
}
