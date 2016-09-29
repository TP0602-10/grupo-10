package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableCellException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ImmutableCellTest {

    @Test(expected = ImmutableCellException.class)
    public void setContent() throws Exception {
        ImmutableCell cell = new ImmutableCell(new MutableContent<>(1,"value1"));
        cell.setContent(new MutableContent<>(2,"value2"));
    }

    @Test(expected = ImmutableCellException.class)
    public void setValue() throws Exception {
        ImmutableCell cell = new ImmutableCell(new MutableContent<>(1,"value"));
        cell.setValue(12,"value");
    }

    @Test
    public void getValueInitial() throws NoFindContentbyTagException {
        ImmutableCell cell = new ImmutableCell(new MutableContent<>(1,"value"));
        assertEquals(1,cell.getValue("value"));
        assertEquals(1,cell.getValue());
    }

    @Test(expected = ImmutableCellException.class)
    public void setValueWithoutTag() {
        List<Content> list = new ArrayList<>();
        list.add(new MutableContent<>(1,"value1"));
        ImmutableCell cell = new ImmutableCell(list);

        cell.setValue(2);
    }
}