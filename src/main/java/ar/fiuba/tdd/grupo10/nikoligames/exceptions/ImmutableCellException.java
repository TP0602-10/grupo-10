package ar.fiuba.tdd.grupo10.nikoligames.exceptions;

/**
 * Exception throw when a cell is immutable.
 */
public class ImmutableCellException extends Exception {
    public ImmutableCellException(String msg){
        super(msg);
    }
}
