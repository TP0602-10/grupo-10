package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Number;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.grupo10.nikoligames.grid.cells.CellFactory.newMutableCell;
import static org.junit.Assert.*;

public class MutableCellTest {
    @Test
    public void setContent() throws Exception {
        Cell cell = newMutableCell(new MutableContent<>(new Number("1"),"value1"));
        cell.setContent(new MutableContent<>(new Number("2"),"value2"));
        Content content = cell.getContent("value2");
        assertEquals(content.getTag(),"value2");
        assertEquals(content.getValue(),2);
    }

    @Test
    public void setValueUniqueContent() throws Exception {
        Cell cell = newMutableCell(new MutableContent<>(new Number("1"),"value1"));
        cell.setValue(4,"value1");
        assertEquals(cell.getValue(),4);
    }

    @Test
    public void setValue() throws Exception {
        Cell cell = newMutableCell(new MutableContent<>(new Number("1"),"value1"));
        cell.setValue(4,"value1");
        assertEquals(cell.getValue("value1"),4);
    }

    @Test(expected = NoFindContentbyTagException.class)
    public void setValueWithoutTagInCellWithManyContent() {
        List<Content> list = new ArrayList<>();
        list.add(new MutableContent<>(new Number("1"),"value1"));
        list.add(new MutableContent<>(new Number("2"),"value2"));
        Cell cell = newMutableCell(list);

        cell.setValue(2);
    }

    @Test(expected = NoFindContentbyTagException.class)
    public void setValueWithoutInvalidTag() {
        Cell cell = newMutableCell(new MutableContent<>(new Number("1"),"value1"));

        cell.setValue(2,"invalidTag");
    }

}