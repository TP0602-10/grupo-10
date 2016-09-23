package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIteratorHelper;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Rule operation that checks if all the cell contents of matter for the rule are distinct between them.
 * It uses the generic Object#equals() so it may not know the actual content type of the cell.
 */
public class DistinctOperation extends GridRuleOperation<Boolean> {

    public DistinctOperation(List<String> contentTags) {
        super(contentTags);
    }

    @Override
    public Boolean perform(GridRuleIterator iterator, Object... params) {
        Boolean allDistinct = Boolean.TRUE;
        while (iterator.hasNext()) {
            GridCell cell = iterator.next();
            if (isApplicableOn(cell)) {
                allDistinct = areAllContentsDistinct(cell.getContents(getContentTags()));
            }
            if (! allDistinct) {
                break;
            }
        }
        return allDistinct;
    }

    @Override
    public boolean isApplicableOn(GridCell cell) {
        return cell.areRulesApplicable()
                && ! cell.getContents(getContentTags()).isEmpty();
    }

    @Override
    public String getOperationExplanation(Boolean result) {
        return "The operation returns TRUE if all the cell contents are distinct. The result is " + result.toString() + ".";
    }

    private boolean areAllContentsDistinct(List<CellContent> contents) {
        return ListHelper.equals(
                contents,
                ListHelper.rejectDuplicateElements(contents)
        );
    }
}
