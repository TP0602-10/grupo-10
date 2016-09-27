package ar.fiuba.tdd.grupo10.nikoligames.exceptions;

/**
 * Exception thrown when not find a content in cell for a tag.
 */
public class NoFindContentbyTagException extends Exception{
    public NoFindContentbyTagException(String msg){
        super(msg);
    }
}
