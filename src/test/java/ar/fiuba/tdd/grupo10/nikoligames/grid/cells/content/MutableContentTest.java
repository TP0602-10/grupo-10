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
        assertTrue(content.isValueEditable());
    }

    @Test
    public void clearValue() throws Exception {
        MutableContent<Integer> content = new MutableContent<>(1,"value1");
        assertFalse(content.isEmpty());
        assertTrue(content.clearValue());
        assertTrue(content.isEmpty());
        assertFalse(content.clearValue());
    }

    @Test
    public void isEmpty() throws Exception {
        MutableContent<Integer> content = new MutableContent<>(null,"value1");
        assertTrue(content.isEmpty());
        content.setValue(2);
        assertFalse(content.isEmpty());
    }

    @Test
    public void equals() throws Exception {
        MutableContent<Integer> content1 = new MutableContent<>(1,"value1");
        MutableContent<Integer> content2 = new MutableContent<>(1,"value1");
        MutableContent<Integer> content3 = new MutableContent<>(1,"value2");
        MutableContent<Integer> content4 = new MutableContent<>(2,"value1");

        assertTrue(content1.equals(content2));
        assertFalse(content1.equals(content3));
        assertFalse(content2.equals(content4));
        assertTrue(content1.equals(content1));
        assertFalse(content1.equals(null));
        assertFalse(content1.equals("OtherClass"));
    }

    @Test
    public void hashCodeContent() {
        MutableContent<Integer> content1 = new MutableContent<>(1,"value1");
        assertNotNull(content1.hashCode());
    }

}