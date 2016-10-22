package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;


import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Number;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BoundariesManagerTests {
    private Cell cell1;
    private Cell cell2;
    private Cell cell3;
    private Cell cell4;

    private Cell createCell(int value) {
        return new Cell( new MutableContainer( new MutableContent<>(new Number(Integer.toString(value)),"CELL")) );
    }

    private Container createContainer(int value) {
        return new Container( new MutableContainer( new MutableContent<>(new Number(Integer.toString(value)),"CONTAINER")) );
    }

    private void generateNeighbours() {
        /*
            c1   c2
               .
            c3   c4
         */

        cell1.setNeighbourAt(cell2, NeighbourPosition.RIGHT);
        cell1.setNeighbourAt(cell3, NeighbourPosition.BOTTOM);
        cell1.setNeighbourAt(cell4, NeighbourPosition.BOTTOM_RIGHT);

        cell2.setNeighbourAt(cell3, NeighbourPosition.BOTTOM_LEFT);
        cell2.setNeighbourAt(cell4, NeighbourPosition.BOTTOM);

        cell3.setNeighbourAt(cell4,NeighbourPosition.RIGHT);
    }

    @Before
    public void setUp() {
        cell1 = createCell(1);
        cell2 = createCell(2);
        cell3 = createCell(3);
        cell4 = createCell(4);

    }

    @Test
    public void rightNeighbourShareBorderAndCorner() {
        Container containerForCell1 = createContainer(1);
        cell1.setLimitAt(containerForCell1,NeighbourPosition.RIGHT);
        cell1.setLimitAt(containerForCell1,NeighbourPosition.TOP_RIGHT);
        cell1.setLimitAt(containerForCell1,NeighbourPosition.BOTTOM_RIGHT);

        Container containerForCell2 = createContainer(2);
        cell2.setLimitAt(containerForCell2,NeighbourPosition.LEFT);
        cell2.setLimitAt(containerForCell2,NeighbourPosition.TOP_LEFT);
        cell2.setLimitAt(containerForCell2,NeighbourPosition.BOTTOM_LEFT);

        cell1.setNeighbourAt(cell2, NeighbourPosition.RIGHT);

        Assert.assertEquals( cell2, cell1.getNeighbourAt(NeighbourPosition.RIGHT) );

        Assert.assertEquals( cell1, cell2.getNeighbourAt(NeighbourPosition.LEFT) );

        Container rightBoundaryFromCell1 = cell1.getLimitAt(NeighbourPosition.RIGHT);
        Container leftBoundaryFromCell2 = cell2.getLimitAt(NeighbourPosition.LEFT);
        Assert.assertEquals( rightBoundaryFromCell1.getValue(), leftBoundaryFromCell2.getValue() );

    }

    @Test
    public void fourCellsShareSameCorner() {

        cell1.setLimitAt(createContainer(1), NeighbourPosition.BOTTOM_RIGHT);
        cell2.setLimitAt(createContainer(2), NeighbourPosition.BOTTOM_LEFT);
        cell3.setLimitAt(createContainer(3), NeighbourPosition.TOP_RIGHT);
        cell4.setLimitAt(createContainer(4), NeighbourPosition.TOP_LEFT);

        generateNeighbours();

        Container corner1 = cell1.getLimitAt(NeighbourPosition.BOTTOM_RIGHT);
        Container corner2 = cell2.getLimitAt(NeighbourPosition.BOTTOM_LEFT);
        Container corner3 = cell3.getLimitAt(NeighbourPosition.TOP_RIGHT);
        Container corner4 = cell4.getLimitAt(NeighbourPosition.TOP_LEFT);

        Assert.assertEquals(corner1,corner2);
        Assert.assertEquals(corner1,corner3);
        Assert.assertEquals(corner1,corner4);

        Assert.assertEquals(corner2,corner3);
        Assert.assertEquals(corner2,corner4);

        Assert.assertEquals(corner3,corner4);

        Assert.assertEquals( corner1.getValue(), 1 );
    }

}
