package ar.fiuba.tdd.grupo10.nikoligames.exceptions;

/**
 * Exception thrown when the number of cells specified to the GameGridBuilder don't match
 * the specified dimensions of such grid.
 */
public class WrongNumberOfGridCellsException extends NikoliException {
    public WrongNumberOfGridCellsException(String msg) {
        super(msg);
    }
}
