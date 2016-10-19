package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operation;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.AlwaysVerifiableRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleCondition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.CountFilledOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CountFilledOperationTests {
    GridRule<Integer> theRule;

    private Cell createCell(int value, String tag) {
        return new Cell( new MutableContainer( new MutableContent<>(value,tag) ) );
    }



    @Before
    public void setUp() {
        String tag = "tag";
        String otherTag = "gat";
        List<Container> cells = new ArrayList<>();
        cells.add( createCell(1,tag) );
        cells.add( createCell(41,otherTag) );
        cells.add( createCell(2,otherTag) );
        cells.add( createCell(213,tag) );
        cells.add( createCell(1,otherTag) );
        cells.add( createCell(6477,otherTag) );

        GridRuleIterator iterator = new GridRuleIterator( cells, "Iterator for CountFilledOperationTests" );

        GridRuleOperation<Integer> operation = new CountFilledOperation(tag);

        GridRuleCondition<Integer> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                2
        );

        theRule = new AlwaysVerifiableRule<>(
                iterator,
                operation,
                condition
        );

    }

    @Test(expected = Test.None.class)
    public void theCountFilledIsEqualThanExpected() {
        theRule.verifyRule();
    }

}
