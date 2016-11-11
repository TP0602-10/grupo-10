package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import org.apache.commons.lang3.StringUtils;

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
        if (content != null && !content.isEmpty()) {
            if ( content.getValue() instanceof String ) {
                String value = (String)content.getValue();
                return !StringUtils.isEmpty(value);
            }
            return true;
        }
        return false;
    }

    @Override
    public String getOperationExplanation(Integer result) {
        return "The operation count the filled contents for the assigned tag on the iterator. The result is " + result + ".";
    }
}
