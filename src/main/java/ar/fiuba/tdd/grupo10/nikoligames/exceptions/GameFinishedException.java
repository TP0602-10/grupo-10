package ar.fiuba.tdd.grupo10.nikoligames.exceptions;

/**
 * Exception thrown when the current game has finished.
 */
public class GameFinishedException extends NikoliException {
    public GameFinishedException(String msg) {
        super(msg);
    }
}
