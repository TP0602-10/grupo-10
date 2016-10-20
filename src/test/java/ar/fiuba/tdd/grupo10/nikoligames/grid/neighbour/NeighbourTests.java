package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.InvalidNeighbour;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.RightNeighbour;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NeighbourTests {
    private static String TEST_TAG = "_";

    Cell cellOne;
    Cell cellTwo;
    Cell cellThree;

    @Before
    public void setUp() {
        cellOne     = new Cell(new MutableContainer(new MutableContent<>(1, TEST_TAG)));
        cellTwo     = new Cell(new MutableContainer(new MutableContent<>(2, TEST_TAG)));
        cellThree   = new Cell(new MutableContainer(new MutableContent<>(3, TEST_TAG)));

        cellOne.setNeighbourAt( cellTwo, NeighbourPosition.RIGHT );
        cellTwo.setNeighbourAt( cellOne, NeighbourPosition.LEFT );
    }

    @Test
    public void cellOneNeighbours() {
        Assert.assertEquals( cellOne.getNeighbourAt(NeighbourPosition.RIGHT), cellTwo );
        Assert.assertEquals( cellOne.getNeighbourAt(NeighbourPosition.LEFT), null );
        Assert.assertEquals( cellOne.getNeighbourAt(NeighbourPosition.TOP), null );
        Assert.assertEquals( cellOne.getNeighbourAt(NeighbourPosition.BOTTOM), null );

        Assert.assertTrue( cellOne.getNeighbourFrom( cellTwo ) instanceof RightNeighbour);
        Assert.assertTrue( cellOne.getNeighbourFrom( cellThree ) instanceof InvalidNeighbour);

    }

    @Test
    public void cellOneAndcellTwoAreNeighbour() {

        Assert.assertEquals( cellOne.getNeighbourAt(NeighbourPosition.RIGHT), cellTwo );
        Assert.assertEquals( cellTwo.getNeighbourAt(NeighbourPosition.LEFT), cellOne );

    }


}
