package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.NullValue;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.StringValue;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Value;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.HorizontalLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConcatenateStringOperationTest {
    private static final String TAG = "TAG";

    private Container createContainerWithTag(Value value, String tag) {
        return new Container( new MutableContainer(new MutableContent<>(value, tag) ) );
    }

    private Container createContainer(String value ) {
        StringValue theValue = new StringValue(value);
        return createContainerWithTag(theValue,TAG);
    }

    @Test
    public void wordInContainers() {
        List<Container> containers = new ArrayList<>();
        containers.add( createContainer("H") );
        containers.add( createContainer("E") );
        containers.add( createContainer("L") );
        containers.add( createContainer("L") );
        containers.add( createContainer("O") );

        GridRuleIterator iterator = new GridRuleIterator( containers, "Test Iterator" );
        ConcatenateStringOperation operation = new ConcatenateStringOperation(TAG);

        String result = operation.perform(iterator);

        Assert.assertEquals(result,"HELLO");
    }

    @Test
    public void oneContainerIsNotApplicable() {
        List<Container> containers = new ArrayList<>();
        containers.add( createContainer("H") );
        containers.add( createContainerWithTag( new NullValue(), "OtherTag") );
        containers.add( createContainerWithTag( new HorizontalLine("Horizontal"), TAG) );
        containers.add( createContainer("L") );

        GridRuleIterator iterator = new GridRuleIterator( containers, "Test Iterator" );
        ConcatenateStringOperation operation = new ConcatenateStringOperation(TAG);

        String result = operation.perform(iterator);

        Assert.assertEquals(result,"HL");

    }

}
