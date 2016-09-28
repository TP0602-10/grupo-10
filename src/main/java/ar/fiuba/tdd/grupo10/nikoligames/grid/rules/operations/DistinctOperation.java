package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Rule operation that checks if all the cell contents of matter for the rule are distinct between them.
 * It uses the generic Object#equals() so it may not know the actual content type of the cell.
 */
public class DistinctOperation extends GridRuleOperation<Boolean> {

    public DistinctOperation(List<String> contentTags) {
        super(contentTags);
    }

    public DistinctOperation(String tag) {
        super(tag);
    }

    @Override
    public Boolean perform(GridRuleIterator iterator, Object... params) {
        List<Content> contentsOfCells = new ArrayList<>();

        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            contentsOfCells.addAll( cell.getContents(getContentTags()) );
        }
        return areAllContentsDistinct(contentsOfCells);
    }

    @Override
    public boolean isApplicableOn(Cell cell) {
        return ! cell.getContents(getContentTags()).isEmpty();
    }

    @Override
    public String getOperationExplanation(Boolean result) {
        return "The operation returns TRUE if all the cell contents are distinct. The result is " + result.toString() + ".";
    }

    private boolean areAllContentsDistinct(List<Content> contents) {
        List<Object> valuesOfContents = contents.stream().map(Content::getValue).collect(Collectors.toList());
        return ListHelper.equals(
                valuesOfContents,
                ListHelper.rejectDuplicateElements(valuesOfContents)
        );
    }
}
