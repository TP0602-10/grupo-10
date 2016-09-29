package ar.fiuba.tdd.grupo10.nikoligames.grid;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.WrongNumberOfGridCellsException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;

import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    }

}
