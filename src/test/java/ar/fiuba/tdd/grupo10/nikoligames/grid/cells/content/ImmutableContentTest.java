package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Number;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableContentTest {
    @Test
    public void getValue() throws Exception {
        ImmutableContent<Integer> content = new ImmutableContent<>(new Number("1"),"value1");
        int value = content.getValue();
        assertEquals(value,1);
    }

    @Test(expected = ImmutableContentValueException.class)
    public void setValue() throws Exception {
        ImmutableContent<Integer> content = new ImmutableContent<>(new Number("1"),"value1");
        content.setValue(2);
    }

    @Test
    public void getTag() throws Exception {
        ImmutableContent<Integer> content = new ImmutableContent<>(new Number("1"),"value1");
        assertEquals(content.getTag(),"value1");
    }

    @Test
    public void isValueEditable() throws Exception {
        ImmutableContent<Integer> content = new ImmutableContent<>(new Number("1"),"value1");
        assertEquals(content.isValueEditable(),false);
    }

    @Test
    public void clearValue() throws Exception {
        ImmutableContent<Integer> content = new ImmutableContent<>(new Number("1"),"value1");
        assertEquals(content.clearValue(),false);
        int value = content.getValue();
        assertEquals(value,1);
    }

    @Test
    public void isEmpty() throws Exception {
        ImmutableContent content = new ImmutableContent<>(new Number("1"),"value1");
        assertEquals(content.isEmpty(),false);
        content = new ImmutableContent<>(null,"valueNull");
        assertEquals(content.isEmpty(),true);
    }

}