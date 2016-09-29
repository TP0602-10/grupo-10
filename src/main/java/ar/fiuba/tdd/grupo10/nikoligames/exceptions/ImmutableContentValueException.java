package ar.fiuba.tdd.grupo10.nikoligames.exceptions;

/**
 * Exception thrown when value of content is immutable.
 */
public class ImmutableContentValueException extends NikoliException {
    public ImmutableContentValueException(String msg) {
        super(msg);
    }
}
