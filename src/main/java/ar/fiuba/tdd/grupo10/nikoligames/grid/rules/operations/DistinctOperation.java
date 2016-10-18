package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rule operation that checks if all the cell contents of matter for the rule are distinct between them.
 * It uses the generic Object#equals() so it may not know the actual content type of the cell.
 * It uses the content #toString() because the grid user inputs are strings.
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
        List<Content> contentsOfContainers = new ArrayList<>();

        while (iterator.hasNext()) {
            Container container = iterator.next();
            contentsOfContainers.addAll( container.getContents(getContentTags()) );
        }
        return areAllContentsDistinct(contentsOfContainers);
    }

    @Override
    public boolean isApplicableOn(Container container) {
        return ! container.getContents(getContentTags()).isEmpty();
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return ! content.isEmpty();
    }

    @Override
    public String getOperationExplanation(Boolean result) {
        return "The operation returns TRUE if all the cell contents are distinct. The result is " + result.toString() + ".";
    }

    private boolean areAllContentsDistinct(List<Content> contents) {
        List<Object> valuesOfContents = contents.stream()
                .filter(this::isApplicableOn)
                .map(content -> content.getValue().toString())
                .collect(Collectors.toList());
        return ListHelper.equals(
                valuesOfContents,
                ListHelper.rejectDuplicateElements(valuesOfContents)
        );
    }
}
