package ar.fiuba.tdd.grupo10.nikoligames.exceptions;

/**
 * Exception throw when a cell is immutable.
 */
public class ImmutableContainerException extends NikoliException {
    public ImmutableContainerException(String msg) {
        super(msg);
    }
}
