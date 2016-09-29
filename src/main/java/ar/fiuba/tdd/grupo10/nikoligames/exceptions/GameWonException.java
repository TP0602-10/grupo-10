package ar.fiuba.tdd.grupo10.nikoligames.exceptions;

/**
 * Exception thrown when the player won the game.
 */
public class GameWonException extends GameFinishedException {
    public GameWonException(String msg) {
        super(msg);
    }
}
