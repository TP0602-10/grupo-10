package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operation;


import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.LineContinousOperation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListContinousRuleTests {
    private GridRule<Boolean> lineContinousRule;
    private Cell cell1;
    private Cell cell2;
    private Cell cell3;
    private Cell cell4;


    private Cell createCell(Object contentValue) {
        String dummyTag = "_";
        return new Cell( new MutableContainer( new MutableContent<>(contentValue, dummyTag)) );
    }

    private void createCells() {
        cell1 = createCell( new FromBottomToRightLine() );
        cell2 = createCell( new FromBottomToLeftLine() );
        cell3 = createCell( new FromTopToLeftLine() );
        cell4 = createCell( new FromTopToRightLine() );
    }

    private Container[] generateFourCellsWithCloseLineCircuit() {
        //Simple square
        createCells();

        cell1.setRightNeighbour( cell2 );
        cell2.setBottomNeighbour( cell3 );
        cell3.setLeftNeighbour( cell4 );
        cell4.setTopNeighbour( cell1 );

        Container[] cells = { cell1, cell2, cell3, cell4 };
        return cells;
    }

    private Container[] generateFourCellsWithOpenLineCircuit() {
        //Simple square but las line is modified to do open circuit
        createCells();

        cell1.setRightNeighbour( cell2 );
        cell2.setBottomNeighbour( cell3 );

        cell4 = createCell( new HorizontalLine() );
        cell3.setLeftNeighbour( cell4 );
        cell4.setTopNeighbour( cell1 );

        Container[] cells = { cell1, cell2, cell3, cell4 };
        return cells;
    }

    private void createRule( Container[] containers ) {

        List<Container> containersList = new ArrayList<>( Arrays.asList(containers) );

        GridRuleIterator iterator = new GridRuleIterator(
                containersList,
                "Iterator for ListContinousRuleTests"
        );

        GridRuleOperation<Boolean> continousOperation = new LineContinousOperation("line");

        GridRuleCondition<Boolean> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );

        lineContinousRule = new AlwaysVerifiableRule<>(
                iterator,
                continousOperation,
                condition
        );
    }

    @Test(expected = Test.None.class)
    public void testTheLineContinousRuleWithValidCells() {
        createRule( generateFourCellsWithCloseLineCircuit() );
        lineContinousRule.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void testTheLineContousRuleWithInvalidCells() {
        createRule( generateFourCellsWithOpenLineCircuit() );
        lineContinousRule.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void singleCellInIteratorFails() {
        createCells();
        Container[] cells = {cell1};

        createRule( cells );
        lineContinousRule.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void emptyIteratorFails() {
        Container[] cells = {};

        createRule( cells );
        lineContinousRule.verifyRule();
    }






}
