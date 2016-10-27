package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour;

import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NeighbourPositionTest {

    @Test
    public void associatedRightPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.RIGHT);
        associatedPositions.add(NeighbourPosition.TOP_RIGHT);
        associatedPositions.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.RIGHT.getAssociatedFrontier(), associatedPositions));
    }

    @Test
    public void associatedToLeftPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.LEFT);
        associatedPositions.add(NeighbourPosition.TOP_LEFT);
        associatedPositions.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.LEFT.getAssociatedFrontier(), associatedPositions));
    }

    @Test
    public void associatedToTopPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.TOP);
        associatedPositions.add(NeighbourPosition.TOP_LEFT);
        associatedPositions.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP.getAssociatedFrontier(), associatedPositions));
    }

    @Test
    public void associatedToBottomPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.BOTTOM);
        associatedPositions.add(NeighbourPosition.BOTTOM_LEFT);
        associatedPositions.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM.getAssociatedFrontier(), associatedPositions));
    }

    @Test
    public void associatedToTopLeftPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.TOP_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_LEFT.getAssociatedFrontier(), associatedPositions));
    }

    @Test
    public void associatedToTopRightPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_RIGHT.getAssociatedFrontier(), associatedPositions));
    }

    @Test
    public void associatedToBottomRightPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_RIGHT.getAssociatedFrontier(), associatedPositions));
    }

    @Test
    public void associatedToBottomLeftPositions() {
        List<NeighbourPosition> associatedPositions = new ArrayList<>();
        associatedPositions.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_LEFT.getAssociatedFrontier(), associatedPositions));
    }

    @Test
    public void associatedOppositesToTopPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.BOTTOM);
        associatedOposites.add(NeighbourPosition.BOTTOM_LEFT);
        associatedOposites.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP.getOppositeFrontier(), associatedOposites));
    }

    @Test
    public void associatedOppositesToRightPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.LEFT);
        associatedOposites.add(NeighbourPosition.TOP_LEFT);
        associatedOposites.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.RIGHT.getOppositeFrontier(), associatedOposites));
    }

    @Test
    public void associatedOppositesToBottomPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.TOP);
        associatedOposites.add(NeighbourPosition.TOP_LEFT);
        associatedOposites.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM.getOppositeFrontier(), associatedOposites));
    }

    @Test
    public void associatedOppositesToLeftPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.RIGHT);
        associatedOposites.add(NeighbourPosition.TOP_RIGHT);
        associatedOposites.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.LEFT.getOppositeFrontier(), associatedOposites));
    }

    @Test
    public void associatedOppositesToTopLeftPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.TOP_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_RIGHT.getOppositeFrontier(), associatedOposites));
    }

    @Test
    public void associatedOppositesToTopRightPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.TOP_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.BOTTOM_LEFT.getOppositeFrontier(), associatedOposites));
    }

    @Test
    public void associatedOppositesToBottomRightPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.BOTTOM_RIGHT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_LEFT.getOppositeFrontier(), associatedOposites));
    }

    @Test
    public void associatedOppositesToBottomLeftPositions() {
        List<NeighbourPosition> associatedOposites = new ArrayList<>();
        associatedOposites.add(NeighbourPosition.BOTTOM_LEFT);

        Assert.assertTrue(ListHelper.equals(NeighbourPosition.TOP_RIGHT.getOppositeFrontier(), associatedOposites));
    }

    @Test
    public void getAssociatedToTopPositionLimitsAndOppositesInOrder() {
        List<NeighbourPosition> associatedLimits = new ArrayList<>();
        associatedLimits.add(NeighbourPosition.TOP);
        associatedLimits.add(NeighbourPosition.TOP_LEFT);
        associatedLimits.add(NeighbourPosition.TOP_RIGHT);

        List<NeighbourPosition> associatedOpposites = new ArrayList<>();
        associatedOpposites.add(NeighbourPosition.BOTTOM);
        associatedOpposites.add(NeighbourPosition.BOTTOM_LEFT);
        associatedOpposites.add(NeighbourPosition.BOTTOM_RIGHT);

        Map<NeighbourPosition.RelativeType, List<NeighbourPosition>> topAssociatedAndOppositeFrontiersInOrder =
                NeighbourPosition.TOP.getAssociatedAndOppositeFrontiersInOrder();

        Assert.assertTrue(
                ListHelper.equalsAndInSameOrder(
                        topAssociatedAndOppositeFrontiersInOrder.get(NeighbourPosition.RelativeType.ASSOCIATED),
                        associatedLimits
                )
        );

        Assert.assertTrue(
                ListHelper.equalsAndInSameOrder(
                        topAssociatedAndOppositeFrontiersInOrder.get(NeighbourPosition.RelativeType.OPPOSITE),
                        associatedOpposites
                )
        );
    }
}