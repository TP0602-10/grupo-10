package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.List;

/**
 * Rule operation that returns the multiplication of all the content cells.
 * Content cells evaluated must be Integer and the operation result is Integer as well.
 */
public class ProductOperation extends GridRuleOperation<Integer> {

    public ProductOperation(List<String> contentTags) {
        super(contentTags);
    }

    public ProductOperation(String contentTag) {
        super(contentTag);
    }

    @Override
    public java.lang.Integer perform(GridRuleIterator iterator, Object... params) {
        Integer accProduct = 1;
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            if (isApplicableOn(cell)) {
                accProduct = multipliesContentValueToAccumulativeProduct(accProduct, cell);
            }
        }
        return accProduct;
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
        return "The operation multiplies all the Integer cell contents. The result is " + result.toString() + ".";
    }

    private Integer multipliesContentValueToAccumulativeProduct(Integer accProduct, Cell cell) {
        if (getContentTags() != null) {
            for (String tag : getContentTags()) {
                if (isApplicableOn(cell.getContent(tag))) {
                    accProduct *= (Integer) cell.getContent(tag).getValue();
                }
            }
        }
        return accProduct;
    }
}
