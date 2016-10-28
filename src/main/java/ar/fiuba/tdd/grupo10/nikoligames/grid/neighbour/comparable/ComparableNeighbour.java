package ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.comparable;


public interface ComparableNeighbour {

    boolean isValidOnTop();

    boolean isValidOnBottom();

    boolean isValidOnLeft();

    boolean isValidOnRight();

    boolean isValidOnTopRight();

    boolean isValidOnBottomRight();

    boolean isValidOnBottomLeft();

    boolean isValidOnTopLeft();

}
