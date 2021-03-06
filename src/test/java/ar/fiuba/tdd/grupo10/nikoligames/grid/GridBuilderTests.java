package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Number;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
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

import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newImmutableCell;
import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newMutableCell;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GridBuilderTests {
    private List<Cell> flattenCells;

    @Before
    public void setUp() {
        Cell[] theCells = {
                newImmutableCell(new ImmutableContent<>(new Number("1"),"_")),
                newImmutableCell(new ImmutableContent<>(new Number("2"),"_")),
                newImmutableCell(new ImmutableContent<>(new Number("3"),"_")),
                newImmutableCell(new ImmutableContent<>(new Number("4"),"_"))
        };
        this.flattenCells = new ArrayList<>(Arrays.asList(theCells));
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
        assertNotNull(gridBuilder.addCell(new Cell(new ImmutableContainer(new ImmutableContent<>(new Number("1"),"_")))));
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
        allCells.add(newMutableCell(new MutableContent<>(new Number("1"), "TAG")));
        allCells.add(newMutableCell(new MutableContent<>(new Number("2"), "TAG")));
        allCells.add(newMutableCell(new MutableContent<>(new Number("3"), "TAG")));

        GridRuleIterator simpleIterator = new GridRuleIterator(
                allCells.stream().map(c -> (Container) c).collect(Collectors.toList()),
                "Iterate over all cells"
        );

        GridRule gridRule = new AlwaysVerifiableRule<>(
                simpleIterator,
                operation,
                condition
        );

        GridRuleManager gridRuleManager = new GridRuleManager();
        assertTrue(gridRuleManager.addRule(gridRule));
        assertTrue(gridRuleManager.removeRule(gridRule));
    }

}
