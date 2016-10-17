package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newMutableCell;
import static org.junit.Assert.*;

public class CellTest {
    @Test
    public void getAllContent() throws Exception {
        List<Content> list = new ArrayList<>();
        list.add(new MutableContent<>(1,"value1"));
        list.add(new MutableContent<>(2,"value2"));
        Cell cell = newMutableCell(list);

        assertFalse(cell.getAllContent().isEmpty());
    }

    @Test(expected = NoFindContentbyTagException.class)
    public void getContentWithoutTagInCellWithManyContent() throws Exception {
        List<Content> list = new ArrayList<>();
        list.add(new MutableContent<>(1,"value1"));
        list.add(new MutableContent<>(2,"value2"));
        Cell cell = newMutableCell(list);

        cell.getContent();
    }

    @Test(expected = NoFindContentbyTagException.class)
    public void getValueInvalidTag() {
        Cell cell = newMutableCell(new MutableContent<>(1,"value"));

        cell.getValue("invalidTag");
    }

    @Test
    public void getContents() throws Exception {
        List<Content> list = new ArrayList<>();
        list.add(new MutableContent<>(1,"value1"));
        list.add(new MutableContent<>(2,"value2"));
        Cell cell = newMutableCell(list);

        List<String> tags = new ArrayList<>();
        tags.add("value2");
        tags.add("value3");

        assertFalse(cell.getContents(tags).isEmpty());
    }

    @Test
    public void isEmpty() throws Exception {
        Cell cell = newMutableCell(new MutableContent<>(1,"value"));
        assertFalse(cell.isEmpty());
    }

}