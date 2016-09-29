package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

/**
 * Rule operation that returns the sum of all the content cells.
 * Content cells evaluated must be Integer and the operation result is Integer as well.
 */
public class SumOperation extends GridRuleOperation<Integer> {

    public SumOperation(List<String> contentTags) {
        super(contentTags);
    }

    public SumOperation(String contentTag) {
        super(contentTag);
    }

    @Override
    public java.lang.Integer perform(GridRuleIterator iterator, Object... params) {
        Integer accSum = 0;
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            if (isApplicableOn(cell)) {
                accSum = sumContentValueToAccumulativeSum(accSum, cell);
            }
        }
        return accSum;
    }

    @Override
    public boolean isApplicableOn(Cell cell) {
        return ! cell.getContents(getContentTags()).isEmpty();
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return !content.isEmpty() && content.getValue() instanceof Integer;
    }

    @Override
    public String getOperationExplanation(Integer result) {
        return "The operation sums all the Integer cell contents. The result is " + result.toString() + ".";
    }

    private Integer sumContentValueToAccumulativeSum(Integer accSum, Cell cell) {
        if (getContentTags() != null) {
            for (String tag : getContentTags()) {
                if (isApplicableOn(cell.getContent(tag))) {
                    accSum += (Integer) cell.getContent(tag).getValue();
                }
            }
        }
        return accSum;
    }
}
