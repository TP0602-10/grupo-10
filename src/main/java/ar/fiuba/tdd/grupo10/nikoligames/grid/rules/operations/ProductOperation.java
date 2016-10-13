package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

/**
 * Rule operation that returns the multiplication of all the content cells.
 * Content cells evaluated must be Integer and the operation result is Integer as well.
 */
public class ProductOperation extends IntegerMathOperation {

    public ProductOperation(List<String> contentTags) {
        super(contentTags);
    }

    public ProductOperation(String contentTag) {
        super(contentTag);
    }

    @Override
    protected Integer getInitialAccValue() {
        return 1;
    }

    @Override
    protected Integer operateOnAccValue(Integer accValue, Integer operationValue) {
        return accValue * operationValue;
    }


    @Override
    public String getOperationExplanation(Integer result) {
        return "The operation multiplies all the Integer cell contents. The result is " + result.toString() + ".";
    }
}
