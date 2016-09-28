package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import org.junit.Test;

import static org.junit.Assert.*;

public class MutableCellTest {
    @Test
    public void setContent() throws Exception {
        MutableCell cell = new MutableCell(new MutableContent<>(1,"value1"));
        cell.setContent(new MutableContent<>(2,"value2"));
        Content content = cell.getContent("value2");
        assertEquals(content.getTag(),"value2");
        assertEquals(content.getValue(),2);
    }

    @Test
    public void setValueUniqueContent() throws Exception {
        MutableCell cell = new MutableCell(new MutableContent<>(1,"value1"));
        cell.setValue(4,"value1");
        assertEquals(cell.getValue(),4);
    }

    @Test
    public void setValue() throws Exception {
        MutableCell cell = new MutableCell(new MutableContent<>(1,"value1"));
        cell.setValue(4,"value1");
        assertEquals(cell.getValue("value1"),4);
    }

}