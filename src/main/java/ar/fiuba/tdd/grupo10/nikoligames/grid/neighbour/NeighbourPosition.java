package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.*;

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
}
