package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

import org.junit.Test;

import static org.junit.Assert.*;

public class MutableContentTest {
    @Test
    public void setValue() throws Exception {
        MutableContent<Integer> content = new MutableContent<>(2,"value1");
        content.setValue(1);
        int value = content.getValue();
        assertEquals(value,1);
    }

    @Test
    public void getValue() throws Exception {
        MutableContent<Integer> content = new MutableContent<>(2,"value2");
        int value = content.getValue();
        assertEquals(value,2);
    }

    @Test
    public void getTag() throws Exception {
        MutableContent<Integer> content = new MutableContent<>(null,"valuenull");
        assertEquals(content.getTag(),"valuenull");
    }

    @Test
    public void isValueEditable() throws Exception {
        MutableContent<Integer> content = new MutableContent<>(1,"value1");
        assertEquals(content.isValueEditable(),true);
    }

    @Test
    public void clearValue() throws Exception {
        MutableContent<Integer> content = new MutableContent<>(1,"value1");
        assertEquals(content.clearValue(),true);
        assertEquals(content.isEmpty(),true);
    }

    @Test
    public void isEmpty() throws Exception {
        MutableContent<Integer> content = new MutableContent<>(null,"value1");
        assertEquals(content.isEmpty(),true);
        content.setValue(2);
        assertEquals(content.isEmpty(),false);
    }

}