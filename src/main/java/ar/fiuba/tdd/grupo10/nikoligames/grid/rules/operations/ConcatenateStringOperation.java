package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcatenateStringOperation extends GridRuleOperation<String> {
    private final String tagOfString;

    ConcatenateStringOperation( String tagString ) {
        super( tagString );
        tagOfString = tagString;
    }

    @Override
    public String perform(GridRuleIterator iterator, Object... params) {
        StringBuffer bufferWord = new StringBuffer();
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
        return null;
    }
}
