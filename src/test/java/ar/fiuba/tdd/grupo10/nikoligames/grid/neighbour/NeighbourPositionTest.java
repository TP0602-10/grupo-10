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
    public void associatedToLeftPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.LEFT);
        associatedPositions.add(NeighbourPosition.TOP_LEFT);
        associatedPositions.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.LEFT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedToTopPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.TOP);
        associatedPositions.add(NeighbourPosition.TOP_LEFT);
        associatedPositions.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedToBottomPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.BOTTOM);
        associatedPositions.add(NeighbourPosition.BOTTOM_LEFT);
        associatedPositions.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedToTopLeftPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.TOP_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_LEFT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedToTopRightPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_RIGHT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedToBottomRightPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_RIGHT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedToBottomLeftPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_LEFT.getAssociatedLimitPositions(), associatedPositions));
    }

    @Test
    public void associatedOpositesToTopPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.BOTTOM);
        associatedOposites.add(NeighbourPosition.BOTTOM_LEFT);
        associatedOposites.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP.getAssociatedOposites(), associatedOposites));
    }

    @Test
    public void associatedOpositesToRightPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.LEFT);
        associatedOposites.add(NeighbourPosition.TOP_LEFT);
        associatedOposites.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.RIGHT.getAssociatedOposites(), associatedOposites));
    }

    @Test
    public void associatedOpositesToBottomPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.TOP);
        associatedOposites.add(NeighbourPosition.TOP_LEFT);
        associatedOposites.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM.getAssociatedOposites(), associatedOposites));
    }

    @Test
    public void associatedOpositesToLeftPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.RIGHT);
        associatedOposites.add(NeighbourPosition.TOP_RIGHT);
        associatedOposites.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.LEFT.getAssociatedOposites(), associatedOposites));
    }

    @Test
    public void associatedOpositesToTopLeftPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.TOP_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_RIGHT.getAssociatedOposites(), associatedOposites));
    }

    @Test
    public void associatedOpositesToTopRightPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_LEFT.getAssociatedOposites(), associatedOposites));
    }

    @Test
    public void associatedOpositesToBottomRightPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_LEFT.getAssociatedOposites(), associatedOposites));
    }

    @Test
    public void associatedOpositesToBottomLeftPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_RIGHT.getAssociatedOposites(), associatedOposites));
    }

}