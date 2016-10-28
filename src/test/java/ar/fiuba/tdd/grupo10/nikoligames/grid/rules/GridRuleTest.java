package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.GridBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Number;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.StringValue;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.DistinctOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.ProductOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.SumOperation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newMutableCell;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 This tests the integration of everything related to the rules
 */
public class GridRuleTest {
    private Grid gridWithOneTagContents;
    private Grid gridWithManyTagContents;

    private static final String ONLY_TAG = "_";
    private static final int NUMBER_OF_ROW = 2;
    private static final int NUMBER_OF_COL = 2;

    private static final String[] TAGS_FOR_MANY_CONTENTS = {"a","b","c","d"};
    private static final int NUMBER_OF_CONTENT = TAGS_FOR_MANY_CONTENTS.length;

    private static List<Cell> createCellWithOneTags() {
        Cell[] theCells = {
                newMutableCell(new MutableContent<>(new Number("1"), ONLY_TAG)),
                newMutableCell(new MutableContent<>(new Number("2"), ONLY_TAG)),
                newMutableCell(new MutableContent<>(new Number("3"), ONLY_TAG)),
                newMutableCell(new MutableContent<>(new Number("4"), ONLY_TAG)),
        };
        return new ArrayList<>(Arrays.asList(theCells));
    }

    private static List<Cell> createCellWithManyTags() {
        List<Cell> theCells = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CONTENT; i++) {
            List<Content> contents = new ArrayList<>();
            for (int j = 0; j < NUMBER_OF_CONTENT; j++) {
                contents.add(new MutableContent<>(new Number(Integer.toString(i + 1)), TAGS_FOR_MANY_CONTENTS[j]) );
            }
            theCells.add(new Cell(new MutableContainer(contents)));
        }
        return theCells;
    }

    @Before
    public void setUp() throws WrongNumberOfGridCellsException {

        gridWithOneTagContents = new GridBuilder()
                .setRows(NUMBER_OF_ROW)
                .setColumns(NUMBER_OF_COL)
                .addCells( createCellWithOneTags() ).buildGrid();

        gridWithManyTagContents = new GridBuilder()
                .setRows(NUMBER_OF_ROW)
                .setColumns(NUMBER_OF_COL)
                .addCells( createCellWithManyTags() ).buildGrid();

    }

    private GridRule<Boolean> createRuleFirstRowOnlyTagAreDistinct() {
        final GridRuleOperation<Boolean> operation = new DistinctOperation(ONLY_TAG);
        assertTrue(!operation.isApplicableOn(newMutableCell(new MutableContent<>(new Number("1"),"value"))));
        final GridRuleCondition<Boolean> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );

        GridRuleIterator simpleIterator = GridRuleIteratorFactory.iteratorForRow(
                gridWithOneTagContents.getCells().stream()
                        .map(cells ->
                                cells.stream().map(c -> (Container) c).collect(Collectors.toList())
                        )
                        .collect(Collectors.toList()),
                0
        );

        return new AlwaysVerifiableRule<>(
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
            ImmutableContainerException {
        GridRule<Boolean> theRule = createRuleFirstRowOnlyTagAreDistinct();

        Integer originalValue = (Integer)(gridWithOneTagContents.getCellAt(0,1).getValue());

        gridWithOneTagContents.getCellAt(0,1).setValue(gridWithOneTagContents.getCellAt(0,0).getValue());

        theRule.verifyRule();

        gridWithOneTagContents.getCellAt(0,1).setValue(originalValue);
    }

    private GridRule<Integer> createRuleAllCellAndEspecificSumOperationAndGoal(Integer goal) {
        final GridRuleOperation<Integer> operation = new SumOperation(ONLY_TAG);
        final GridRuleCondition<Integer> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                goal
        );

        List<Cell> allCells = gridWithOneTagContents.getCells().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        GridRuleIterator simpleIterator = new GridRuleIterator(
                allCells.stream().map(c -> (Container) c).collect(Collectors.toList()),
                "Iterate over all cells"
        );

        return new AlwaysVerifiableRule<>(
                simpleIterator,
                operation,
                condition
        );
    }

    private GridRule<Integer> createRuleAllCellAndEspecificProductOperationAndGoal(Integer goal) {
        final GridRuleOperation<Integer> operation = new ProductOperation(ONLY_TAG);
        final GridRuleCondition<Integer> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                goal
        );

        List<Cell> allCells = gridWithOneTagContents.getCells().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        GridRuleIterator simpleIterator = new GridRuleIterator(
                allCells.stream().map(c -> (Container) c).collect(Collectors.toList()),
                "Iterate over all cells"
        );

        return new AlwaysVerifiableRule<>(
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

    @Test(expected = Test.None.class)
    public void productOfAllCellsOfGrid() throws RuleNotSatisfiedException {
        GridRule<Integer> theRule = createRuleAllCellWithManyIntegerContentsAndOperationAndGoal(
                new ProductOperation( Arrays.asList(TAGS_FOR_MANY_CONTENTS) ),
                16 * 81 * 256
        );
        theRule.verifyRule();
    }

    @Test(expected = Test.None.class)
    public void productOfAllCells() throws RuleNotSatisfiedException {
        GridRule<Integer> theRule = createRuleAllCellAndEspecificProductOperationAndGoal(24);
        theRule.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void productOfAllCellsWithInvalidGoal() throws RuleNotSatisfiedException {
        GridRule<Integer> theRule = createRuleAllCellAndEspecificProductOperationAndGoal(80751770);
        theRule.verifyRule();
    }

    private GridRule<Integer> createRuleAllCellWithManyIntegerContentsAndOperationAndGoal(
            GridRuleOperation<Integer> operation,
            Integer goal) {

        final GridRuleCondition<Integer> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                goal
        );

        List<Cell> allCells = gridWithManyTagContents.getCells().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        GridRuleIterator simpleIterator = new GridRuleIterator(
                allCells.stream().map(c -> (Container) c).collect(Collectors.toList()),
                "Iterate over all cells"
        );

        return new AlwaysVerifiableRule<>(
                simpleIterator,
                operation,
                condition
        );
    }

    private GridRule<Integer> createRuleAllCellWithManyContentsAndEspecificSumOperationAndGoal(Integer goal) {

        final GridRuleOperation<Integer> operation = new SumOperation( Arrays.asList(TAGS_FOR_MANY_CONTENTS) );
        final GridRuleCondition<Integer> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                goal
        );

        List<Cell> allCells = gridWithManyTagContents.getCells().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        GridRuleIterator simpleIterator = new GridRuleIterator(
                allCells.stream().map(c -> (Container) c).collect(Collectors.toList()),
                "Iterate over all cells"
        );

        return new AlwaysVerifiableRule<>(
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
        List<Cell> allCells = new ArrayList<>();
        Cell celltest = newMutableCell( new MutableContent<>(new StringValue("M"), ONLY_TAG) );
        allCells.add(celltest);
        allCells.add(newMutableCell( new MutableContent<>(new StringValue("A"), ONLY_TAG) ));
        allCells.add(newMutableCell( new MutableContent<>(new StringValue("N"), ONLY_TAG) ));
        allCells.add(newMutableCell( new MutableContent<>(new StringValue("Y"), ONLY_TAG) ));
        new GridBuilder().setColumns(2).setRows(2).addCells( allCells ).buildGrid();

        final GridRuleOperation<Boolean> operation = new DistinctOperation(ONLY_TAG);
        assertTrue(operation.isApplicableOn(celltest));
        final GridRuleCondition<Boolean> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );

        GridRuleIterator simpleIterator = new GridRuleIterator(
                allCells.stream().map(c -> (Container) c).collect(Collectors.toList()),
                "Iterate over all cells"
        );

        GridRule<Boolean> theRule = new AlwaysVerifiableRule<>(
                simpleIterator,
                operation,
                condition
        );
        theRule.verifyRule();
    }

    @Test
    public void getIterator() {
        List<Cell> allCells = new ArrayList<>();
        allCells.add(newMutableCell( new MutableContent<>(new StringValue("M"), ONLY_TAG) ));
        allCells.add(newMutableCell( new MutableContent<>(new StringValue("A"), ONLY_TAG) ));
        allCells.add(newMutableCell( new MutableContent<>(new StringValue("N"), ONLY_TAG) ));
        allCells.add(newMutableCell( new MutableContent<>(new StringValue("Y"), ONLY_TAG) ));
        new GridBuilder().setColumns(2).setRows(2).addCells( allCells ).buildGrid();

        final GridRuleOperation<Boolean> operation = new DistinctOperation(ONLY_TAG);
        final GridRuleCondition<Boolean> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );

        GridRuleIterator simpleIterator = new GridRuleIterator(
                allCells.stream().map(c -> (Container) c).collect(Collectors.toList()),
                "Iterate over all cells"
        );

        GridRule<Boolean> theRule = new AlwaysVerifiableRule<>(
                simpleIterator,
                operation,
                condition
        );

        assertEquals(simpleIterator,theRule.getRuleIterator());
    }
}
