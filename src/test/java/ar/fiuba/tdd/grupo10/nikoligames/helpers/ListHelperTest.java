package ar.fiuba.tdd.grupo10.nikoligames.helpers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ListHelperTest {

    @Test
    public void mergeTwoList() {
        ArrayList<String> listOne = new ArrayList<String>();
        listOne.add("foo");

        ArrayList<String> listTwo = new ArrayList<String>();
        listTwo.add("bar");

        ArrayList<String> expectedList = new ArrayList<String>();
        expectedList.add("foo");
        expectedList.add("bar");

        assertEquals(expectedList, ListHelper.merge(listOne,listTwo) );
    }

    @Test
    public void twoListsOfStringEqualsAndNotEquals() {
        ArrayList<String> listOne = new ArrayList<String>();
        listOne.add("a");
        listOne.add("z");
        listOne.add("d");
        listOne.add("a");

        ArrayList<String> listTwo = new ArrayList<String>();
        listTwo.add("a");
        listTwo.add("z");
        listTwo.add("d");
        listTwo.add("a");

        assertTrue( ListHelper.equals(listOne,listTwo) );

        listTwo.add("omg :)");
        assertFalse( ListHelper.equals(listOne,listTwo) );
    }


    @Test
    public void rejectDuplicateElementsOfEmptyList() {
        ArrayList<String> theList = new ArrayList<String>();
        theList.add("a");
        theList.add("z");
        theList.add("z");
        theList.add("d");
        theList.add("a");

        ArrayList<String> expectedList = new ArrayList<String>();
        expectedList.add("a");
        expectedList.add("z");
        expectedList.add("d");

        assertEquals(expectedList,ListHelper.rejectDuplicateElements(theList));
    }

    @Test
    public void createSimpleMatrixFromList() {
        ArrayList<Integer> plainList = new ArrayList<Integer>();
        plainList.add(1);
        plainList.add(2);
        plainList.add(3);
        plainList.add(4);

        ArrayList<ArrayList<Integer>> expectedMatrix = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> firstRow = new ArrayList<Integer>();
        firstRow.add(1);
        firstRow.add(2);
        expectedMatrix.add(firstRow);
        ArrayList<Integer> secondRow = new ArrayList<Integer>();
        secondRow.add(3);
        secondRow.add(4);
        expectedMatrix.add(secondRow);

        assertEquals(expectedMatrix, ListHelper.buildMatrixFromFlattenList(plainList,2,2) );
    }
}
