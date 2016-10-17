package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newMutableCell;
import static org.junit.Assert.*;

public class GridRuleIteratorHelperTest {
    @Test
    public void listAllCells() throws Exception {
        List<Cell> list = new ArrayList<>();
        list.add(newMutableCell(new MutableContent<>(1,"number")));
        list.add(newMutableCell(new MutableContent<>(2,"number")));
        list.add(newMutableCell(new MutableContent<>(3,"number")));

        GridRuleIterator iterator = new GridRuleIterator(list,"test");

        List<Cell> listAllCells = GridRuleIteratorHelper.listAllCells(iterator);

        assertFalse(listAllCells.isEmpty());
    }

}