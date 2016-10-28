package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.*;

import java.util.*;

public enum NeighbourPosition {
    TOP(0, new TopNeighbour()),
    TOP_RIGHT(1, new TopRightNeighbour()),
    RIGHT(2, new RightNeighbour()),
    BOTTOM_RIGHT(3, new BottomRightNeighbour()),
    BOTTOM(4, new BottomNeighbour()),
    BOTTOM_LEFT(5, new BottomLeftNeighbour()),
    LEFT(6, new LeftNeighbour()),
    TOP_LEFT(7, new TopLeftNeighbour());

    public static final int ALLOWED_POSITIONS = 8;
    public enum RelativeType {
        ASSOCIATED,
        OPPOSITE
    }

    private int index;
    private NeighbourType type;
    NeighbourPosition(int index, NeighbourType type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public NeighbourType getType() {
        return type;
    }

    public NeighbourPosition getOpposite() {
        return NeighbourPosition.values() [ (this.index + ALLOWED_POSITIONS / 2) % ALLOWED_POSITIONS ];
    }

    public List<NeighbourPosition> getAssociatedFrontier() {
        List<NeighbourPosition> limits = new ArrayList<>();
        limits.add( this );
        if (!doesPositionRepresentACorner()) {
            if (this.index == 0) {
                limits.add(NeighbourPosition.values()[ALLOWED_POSITIONS - 1]);
            } else {
                limits.add(NeighbourPosition.values()[index - 1]);
            }
            limits.add(NeighbourPosition.values()[(index + 1) % ALLOWED_POSITIONS]);
        }
        return limits;
    }

    public List<NeighbourPosition> getOppositeFrontier() {
        return this.getOpposite().getAssociatedFrontier();
    }

    public Map<RelativeType, List<NeighbourPosition>> getAssociatedAndOppositeFrontiersInOrder() {
        Map<RelativeType, List<NeighbourPosition>> associatedAndOppositeFrontiersInOrder = new HashMap<>();
        List<NeighbourPosition> associatedLimits = this.getAssociatedFrontier();
        List<NeighbourPosition> opposites = this.getOppositeFrontier();

        // There is nothing to sort if position represents a corner, lists contain only 1 position in such case.
        if (!this.doesPositionRepresentACorner()) {
            if (associatedLimits.get(1).getOpposite() == opposites.get(1)) {
                // We should swap because opposites aren't such in a frontal limit. Opposites must be adjacent.
                Collections.swap(opposites, 1, 2);
            }
        }

        associatedAndOppositeFrontiersInOrder.put(RelativeType.ASSOCIATED, associatedLimits);
        associatedAndOppositeFrontiersInOrder.put(RelativeType.OPPOSITE, opposites);
        return associatedAndOppositeFrontiersInOrder;
    }

    private boolean doesPositionRepresentACorner() {
        return this == TOP_LEFT || this == TOP_RIGHT
                || this == BOTTOM_RIGHT || this == BOTTOM_LEFT;
    }
}
