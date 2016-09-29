package ar.fiuba.tdd.grupo10.nikoligames.exceptions;

/**
 * Exception thrown when certain rule is not satisfied.
 */
public class RuleNotSatisfiedException extends NikoliException {
    public RuleNotSatisfiedException(String msg) {
        super(msg);
    }
}
