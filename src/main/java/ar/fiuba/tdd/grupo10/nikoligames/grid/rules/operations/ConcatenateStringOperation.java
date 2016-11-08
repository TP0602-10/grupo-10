package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

public class ConcatenateStringOperation extends GridRuleOperation<String> {
    private final String tagOfString;

    public ConcatenateStringOperation( String tagString ) {
        super( tagString );
        tagOfString = tagString;
    }

    @Override
    public String perform(GridRuleIterator iterator, Object... params) {
        StringBuilder bufferWord = new StringBuilder();
        while ( iterator.hasNext() ) {
            Container container = iterator.next();
            if ( isApplicableOn( container ) ) {
                bufferWord.append( (String) container.getValue(tagOfString) );
            }
        }
        return bufferWord.toString();
    }

    @Override
    public boolean isApplicableOn(Container container) {
        Content content = container.getContent(tagOfString);
        return isApplicableOn(content);
    }

    @Override
    public boolean isApplicableOn(Content content) {
        return content != null && !content.isEmpty() && content.getValue() instanceof String;
    }

    @Override
    public String getOperationExplanation(String result) {
        return "Thee operation concatenate as string all values of the container for the assigned tag on the iterator. The result is " + result + ".";
    }
}
