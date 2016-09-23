package ar.fiuba.tdd.grupo10.nikoligames.exceptions;

/**
 * Exception thrown when value of content is immutable.
 */
public class ImmutableContentValueException extends Exception {
    public ImmutableContentValueException(String msg) {
        super(msg);
    }
}
