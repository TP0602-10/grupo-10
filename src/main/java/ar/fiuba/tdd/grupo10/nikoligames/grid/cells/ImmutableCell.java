package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableCellException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Cell whose content cannot be changed.
 */
public class ImmutableCell extends Cell{

    public ImmutableCell(List<Content> contentsList) {
        super(contentsList);
    }

    public ImmutableCell(Content content) {
        super(content);
    }

    @Override
    public void setContent(Content content) throws ImmutableCellException {
        throw new ImmutableCellException("Immutable cell.");
    }

    @Override
    public void setValue(Object value, String contentTag) throws ImmutableCellException {
        throw new ImmutableCellException("Immutable cell");
    }

    @Override
    public void setValue(Object value) throws ImmutableCellException {
        throw new ImmutableCellException("Immutable cell");
    }

    @Override
    public boolean isContentEditable() {
        return false;
    }
}
