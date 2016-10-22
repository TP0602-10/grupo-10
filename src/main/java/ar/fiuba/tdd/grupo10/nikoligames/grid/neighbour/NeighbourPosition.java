package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.*;

import java.util.ArrayList;
import java.util.List;

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

    public NeighbourPosition getOposite() {
        return NeighbourPosition.values() [ (this.index + ALLOWED_POSITIONS / 2) % ALLOWED_POSITIONS ];
    }

    public List<NeighbourPosition> getAssociatedLimitPositions() {
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

    public List<NeighbourPosition> getAssociatedOposites() {
        return this.getOposite().getAssociatedLimitPositions();
    }

    private boolean doesPositionRepresentACorner() {
        return this == TOP_LEFT || this == TOP_RIGHT
                || this == BOTTOM_RIGHT || this == BOTTOM_LEFT;
    }
}
