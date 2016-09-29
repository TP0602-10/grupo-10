package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

/**
 * Rule operation that returns the sum of all the content cells.
 * Content cells evaluated must be Integer and the operation result is Integer as well.
 */
public class SumOperation extends IntegerMathOperation {

    public SumOperation(List<String> contentTags) {
        super(contentTags);
    }

    public SumOperation(String contentTag) {
        super(contentTag);
    }

    @Override
    protected Integer getInitialAccValue() {
        return 0;
    }

    @Override
    protected Integer operateOnAccValue(Integer accValue, Integer operationValue) {
        return accValue + operationValue;
    }

    @Override
    public String getOperationExplanation(Integer result) {
        return "The operation sums all the Integer cell contents. The result is " + result.toString() + ".";
    }

}
