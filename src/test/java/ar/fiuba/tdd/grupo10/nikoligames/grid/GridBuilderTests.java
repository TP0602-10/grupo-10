package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleCondition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleManager;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.SumOperation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GridBuilderTests {
    private List<Cell> flattenCells;

    @Before
    public void setUp() {
        Cell[] theCells = {
                new ImmutableCell(new ImmutableContent<>(1,"_")),
                new ImmutableCell(new ImmutableContent<>(2,"_")),
                new ImmutableCell(new ImmutableContent<>(3,"_")),
                new ImmutableCell(new ImmutableContent<>(4,"_"))
        };
        this.flattenCells = new ArrayList<Cell>(Arrays.asList(theCells));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void buildGridWithCorrectValues() throws WrongNumberOfGridCellsException {
        Grid builtGrid = new GridBuilder().setRows(2).setColumns(2).addCells(flattenCells).buildGrid();

        assertEquals(builtGrid.getCellAt(0,0).getContent("_").getValue(),1);
        assertEquals(builtGrid.getCellAt(0,1).getContent("_").getValue(),2);
        assertEquals(builtGrid.getCellAt(1,0).getContent("_").getValue(),3);
        assertEquals(builtGrid.getCellAt(1,1).getContent("_").getValue(),4);

        assertEquals(builtGrid.getCellAt(1,3).getContent("_").getValue(),4);
    }

    @Test(expected = WrongNumberOfGridCellsException.class)
    public void buildGridWithIncorrectColumnNumber() throws WrongNumberOfGridCellsException {
        new GridBuilder().setRows(2).setColumns(3).addCells(flattenCells).buildGrid();
    }

    @Test(expected = WrongNumberOfGridCellsException.class)
    public void buildGridWithIncorrectRowNumber() throws WrongNumberOfGridCellsException {
        new GridBuilder().setRows(5).setColumns(2).addCells(flattenCells).buildGrid();
    }

    @Test
    public void addCell() {
        GridBuilder gridBuilder = new GridBuilder();
        assertNotNull(gridBuilder.addCell(new ImmutableCell(new ImmutableContent<>(1,"_"))));
    }

    @Test
    public void addObservers() {
        GridBuilder gridBuilder = new GridBuilder();
        Collection<OnGridUpdatedObserver> observers = new ArrayList<>();

        observers.add(new GridRuleManager());
        assertNotNull(gridBuilder.addObservers(observers));

        final GridRuleOperation<Integer> operation = new SumOperation("TAG");
        final GridRuleCondition<Integer> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                1
        );

        List<Cell> allCells = new ArrayList<>();
        allCells.add(new MutableCell(new MutableContent<>(1, "TAG")));
        allCells.add(new MutableCell(new MutableContent<>(2, "TAG")));
        allCells.add(new MutableCell(new MutableContent<>(3, "TAG")));

        GridRuleIterator simpleIterator = new GridRuleIterator(allCells,"Iterate over all cells");

        GridRule gridRule = new GridRule<>(
                simpleIterator,
                operation,
                condition
        );

        GridRuleManager gridRuleManager = new GridRuleManager();
        assertTrue(gridRuleManager.addRule(gridRule));
        assertTrue(gridRuleManager.removeRule(gridRule));
    }

}
