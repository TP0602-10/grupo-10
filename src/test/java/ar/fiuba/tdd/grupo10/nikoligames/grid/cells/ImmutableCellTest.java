package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContainerException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Number;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newImmutableCell;
import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newMutableCell;
import static org.junit.Assert.*;

public class ImmutableCellTest {

    @Test(expected = ImmutableContainerException.class)
    public void setContent() throws Exception {
        Cell cell = newImmutableCell(new MutableContent<>(new Number("1"),"value1"));
        cell.setContent(new MutableContent<>(new Number("2"),"value2"));
    }

    @Test(expected = ImmutableContainerException.class)
    public void setValue() throws Exception {
        Cell cell = newImmutableCell(new MutableContent<>(new Number("1"),"value"));
        cell.setValue(12,"value");
    }

    @Test(expected = NoFindContentbyTagException.class)
    public void getValueInitial() throws NoFindContentbyTagException {
        Cell cell = newImmutableCell(new MutableContent<>(new Number("1"),"value1"));
        assertEquals(1,cell.getValue("value"));
        assertEquals(1,cell.getValue());
    }

    @Test(expected = ImmutableContainerException.class)
    public void setValueWithoutTag() {
        List<Content> list = new ArrayList<>();
        list.add(new MutableContent<>(new Number("1"),"value1"));
        Cell cell = newImmutableCell(list);

        cell.setValue(2);
    }
}