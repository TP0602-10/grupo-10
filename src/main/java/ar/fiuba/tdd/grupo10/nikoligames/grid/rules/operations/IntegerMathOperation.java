package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

/**
 * Abtract operation that returns a basic math operation of all the content cells.
 * The children must know which operation to perform.
 * Content cells evaluated must be Integer and the operation result is Integer as well.
 */
public abstract class IntegerMathOperation extends GridRuleOperation<Integer> {

    public IntegerMathOperation(List<String> contentTags) {
        super(contentTags);
    }

    public IntegerMathOperation(String contentTag) {
        super(contentTag);
    }

    @Override
    public java.lang.Integer perform(GridRuleIterator iterator, Object... params) {
        Integer accValue = getInitialAccValue();
        while (iterator.hasNext()) {
            Container container = iterator.next();
            if (isApplicableOn(container)) {
                accValue = operationContentValueToAccumulativeValue(accValue, container);
            }
        }
        return accValue;
    }

    protected abstract Integer getInitialAccValue();

    @Override
    public boolean isApplicableOn(Container container) {
        return ! container.getContents(getContentTags()).isEmpty();
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return !content.isEmpty() && content.getValue() instanceof Integer;
    }

    private Integer operationContentValueToAccumulativeValue(Integer accValue, Container container) {
        if (getContentTags() != null) {
            for (String tag : getContentTags()) {
                if (isApplicableOn(container.getContent(tag))) {
                    accValue = operateOnAccValue(accValue, (Integer) container.getContent(tag).getValue());
                }
            }
        }
        return accValue;
    }

    protected abstract Integer operateOnAccValue(Integer accValue, Integer operationValue);
}
