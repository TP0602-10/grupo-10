package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableCellException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import java.util.List;

/**
 * Basic structure of the cell.
 */
public interface Cell {

    List<Content> getAllContent();

    void setContent(Content content) throws ImmutableCellException;

    void setValue(Object value,String contentTag) throws ImmutableContentValueException, ImmutableCellException, NoFindContentbyTagException;

    Content getContent(String tag);

    List<Content> getContents(List<String> tags);

    boolean isEmpty();

}
