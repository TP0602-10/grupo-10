package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour;

import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NeighbourPositionTest {

    @Test
    public void associatedRightPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.RIGHT);
        associatedPositions.add(NeighbourPosition.TOP_RIGHT);
        associatedPositions.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.RIGHT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedLeftPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.LEFT);
        associatedPositions.add(NeighbourPosition.TOP_LEFT);
        associatedPositions.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.LEFT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedTopPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.TOP);
        associatedPositions.add(NeighbourPosition.TOP_LEFT);
        associatedPositions.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedBottomPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.BOTTOM);
        associatedPositions.add(NeighbourPosition.BOTTOM_LEFT);
        associatedPositions.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedTopLeftPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.TOP_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_LEFT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedTopRightPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_RIGHT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedBottomRightPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_RIGHT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedBottomLeftPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_LEFT.getAssociatedLimitPositions(), associatedPositions));
    }

}