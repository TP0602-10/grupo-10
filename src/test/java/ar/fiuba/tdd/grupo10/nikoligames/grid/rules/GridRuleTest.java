package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.SumOperation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 This tests the integration of everything related to the rules
 */
public class GridRuleTest {
    private Grid gridWithOneTagContents;
    private Grid gridWithManyTagContents;

    private static final String onlyTag = "_";
    private static final int numberOfRow = 2;
    private static final int numberOfCol = 2;

    private static final String[] tagsForManyContent = {"a","b","c","d"};
    private static final int numberOfContent = tagsForManyContent.length;

    private static List<Cell> createCellWithOneTags() {
        Cell[] theCells = {
                new MutableCell(new MutableContent(1,onlyTag)),
                new MutableCell(new MutableContent(2,onlyTag)),
                new MutableCell(new MutableContent(3,onlyTag)),
                new MutableCell(new MutableContent(4,onlyTag)),
        };
        return new ArrayList<Cell>(Arrays.asList(theCells));
    }

    private static List<Cell> createCellWithManyTags() {
        List<Cell> theCells = new ArrayList<Cell>();
        for (int i = 0; i < numberOfContent; i++) {
            List<Content> contents = new ArrayList<Content>();
            for (int j = 0; j < numberOfContent; j++) {
                contents.add(new MutableContent(i + 1, tagsForManyContent[j]) );
            }
            theCells.add(new MutableCell(contents));
        }
        return theCells;
    }

    @Before
    public void setUp() throws WrongNumberOfGridCellsException {

        gridWithOneTagContents = new GridBuilder()
                .setRows(numberOfRow)
                .setColumns(numberOfCol)
                .addCells( createCellWithOneTags() ).buildGrid();

        gridWithManyTagContents = new GridBuilder()
                .setRows(numberOfRow)
                .setColumns(numberOfCol)
                .addCells( createCellWithManyTags() ).buildGrid();

    }

    private GridRule<Boolean> createRuleFirstRowOnlyTagAreDistinct() {
        final GridRuleOperation operation = new DistinctOperation(onlyTag);
        final GridRuleCondition<Boolean> condition = new GridRuleCondition<Boolean>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );

        GridRuleIterator simpleIterator = GridRuleIteratorFactory.iteratorForRow(
                gridWithOneTagContents.getCells(),
                0
        );

        return new GridRule<Boolean>(
                simpleIterator,
                operation,
                condition
        );
    }

    @Test(expected = Test.None.class)
    public void twoCellsAreDistincts() throws RuleNotSatisfiedException {
        GridRule<Boolean> theRule = createRuleFirstRowOnlyTagAreDistinct();
        theRule.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void twoCellsAreDistinctsButGridIsModifiedToBeEquals() throws RuleNotSatisfiedException,
            NoFindContentbyTagException,
            ImmutableContentValueException,
            ImmutableCellException {
        GridRule<Boolean> theRule = createRuleFirstRowOnlyTagAreDistinct();

        Integer originalValue = (Integer)(gridWithOneTagContents.getCellAt(0,1).getValue());

        gridWithOneTagContents.getCellAt(0,1).setValue(gridWithOneTagContents.getCellAt(0,0).getValue());

        theRule.verifyRule();

        gridWithOneTagContents.getCellAt(0,1).setValue(originalValue);
    }

    private GridRule<Integer> createRuleAllCellAndEspecificSumOperationAndGoal(Integer goal) {
        final GridRuleOperation operation = new SumOperation(onlyTag);
        final GridRuleCondition<Integer> condition = new GridRuleCondition<Integer>(
                new EqualsMatcher<>(),
                goal
        );

        List<Cell> allCells = gridWithOneTagContents.getCells().stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());

        GridRuleIterator simpleIterator = new GridRuleIterator(allCells,"Iterate over all cells");

        return new GridRule<Integer>(
                simpleIterator,
                operation,
                condition
        );
    }

    @Test(expected = Test.None.class)
    public void sumOfAllCells() throws RuleNotSatisfiedException {
        GridRule<Integer> theRule = createRuleAllCellAndEspecificSumOperationAndGoal(10);
        theRule.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void sumOfAllCellsWithInvalidGoal() throws RuleNotSatisfiedException {
        GridRule<Integer> theRule = createRuleAllCellAndEspecificSumOperationAndGoal(41536);
        theRule.verifyRule();
    }


    private GridRule<Integer> createRuleAllCellWithManyContentsAndEspecificSumOperationAndGoal(Integer goal) {

        final GridRuleOperation operation = new SumOperation( Arrays.asList(tagsForManyContent) );
        final GridRuleCondition<Integer> condition = new GridRuleCondition<Integer>(
                new EqualsMatcher<>(),
                goal
        );

        List<Cell> allCells = gridWithManyTagContents.getCells().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        GridRuleIterator simpleIterator = new GridRuleIterator(allCells,"Iterate over all cells");

        return new GridRule<Integer>(
                simpleIterator,
                operation,
                condition
        );
    }

    @Test(expected = Test.None.class)
    public void sumOfAllCellsOfGrid() throws RuleNotSatisfiedException {
        GridRule<Integer> theRule = createRuleAllCellWithManyContentsAndEspecificSumOperationAndGoal(40);
        theRule.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void sumOfAllCellsOfGridWithInvalidGoalSum() throws RuleNotSatisfiedException {
        GridRule<Integer> theRule = createRuleAllCellWithManyContentsAndEspecificSumOperationAndGoal(41536);
        theRule.verifyRule();
    }

    @Test(expected = Test.None.class)
    public void gridOfStringAndSumEqualGoal() throws WrongNumberOfGridCellsException, RuleNotSatisfiedException {
        List<Cell> allCells = new ArrayList<Cell>();
        allCells.add(new MutableCell( new MutableContent("M",onlyTag) ));
        allCells.add(new MutableCell( new MutableContent("A",onlyTag) ));
        allCells.add(new MutableCell( new MutableContent("N",onlyTag) ));
        allCells.add(new MutableCell( new MutableContent("Y",onlyTag) ));
        Grid theGrid = new GridBuilder().setColumns(2).setRows(2).addCells( allCells ).buildGrid();

        final GridRuleOperation operation = new DistinctOperation( onlyTag );
        final GridRuleCondition<Boolean> condition = new GridRuleCondition<Boolean>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );

        GridRuleIterator simpleIterator = new GridRuleIterator(allCells,"Iterate over all cells");

        GridRule<Boolean> theRule = new GridRule<Boolean>(
                simpleIterator,
                operation,
                condition
        );

        theRule.verifyRule();

    }


}
