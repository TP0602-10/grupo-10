package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GridRuleWorkWithOnTagException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Point;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.AlwaysVerifiableRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleCondition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newImmutableCell;
import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newMutableCell;
import static org.junit.Assert.*;

public class SumLinesPointingToSharedCornerOperationTest {
    private final static String lineTag = "line";
    private final static String pointTag = "point";
    private final static String numberTag = "number";

    private GridRule<Integer> sumLinesPointingToSharedCornerRule;
    private Container centralCorner;
    private Cell cell1;
    private Cell cell2;
    private Cell cell3;
    private Cell cell4;

    private Container createCornerWithNumber(Object contentValue, Integer number) {
        List<Content> contents = Arrays.asList(
                new ImmutableContent<>(contentValue, pointTag),
                new ImmutableContent<>(number, numberTag)
        );
        return newImmutableCell(contents);
    }

    private Cell createCell(Object contentValue) {
        return newMutableCell(new MutableContent<>(contentValue, lineTag));
    }

    private void createCells() {
        cell1 = createCell( new FromTopLeftToBottomRightDiagonal() );
        cell2 = createCell( new FromBottomLeftToTopRightDiagonal() );
        cell3 = createCell( new FromTopLeftToBottomRightDiagonal() );
        cell4 = createCell( new FromTopLeftToBottomRightDiagonal() );
    }

    private Container[] generateFourCellsWithThreeLinesMeetingCentralCorner(Integer cornerNumber) {
        createCells();
        centralCorner = createCornerWithNumber(new Point(), cornerNumber);

        cell1.setLimitAt( centralCorner, NeighbourPosition.BOTTOM_RIGHT );
        cell2.setLimitAt( centralCorner, NeighbourPosition.BOTTOM_LEFT );
        cell3.setLimitAt( centralCorner, NeighbourPosition.TOP_RIGHT );
        cell4.setLimitAt( centralCorner, NeighbourPosition.TOP_LEFT );

        Container[] cells = { cell1, cell2, cell3, cell4 };
        return cells;
    }

    private void createRule( Container[] containers ) {

        List<Container> containersList = new ArrayList<>( Arrays.asList(containers) );

        GridRuleIterator iterator = new GridRuleIterator(
                containersList,
                "Iterator for SumLinesPointingToSharedCornerOperationTest"
        );

        GridRuleOperation<Integer> operation = new SumLinesPointingToSharedCornerOperation(
                Arrays.asList(lineTag, pointTag),
                centralCorner
        );

        GridRuleCondition<Integer> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                (Integer) centralCorner.getContent(numberTag).getValue()
        );

        sumLinesPointingToSharedCornerRule = new AlwaysVerifiableRule<>(
                iterator,
                operation,
                condition
        );
    }

    @Test(expected = Test.None.class)
    public void testSumLinesPointingToSharedCornerRuleWith3LinesAndValidCorner() {
        createRule( generateFourCellsWithThreeLinesMeetingCentralCorner(3) );
        sumLinesPointingToSharedCornerRule.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void testSumLinesPointingToSharedCornerRuleWith3LinesAndInvalidCorner() {
        createRule( generateFourCellsWithThreeLinesMeetingCentralCorner(4) );
        sumLinesPointingToSharedCornerRule.verifyRule();
    }

}