package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Cell whose content can be changed.
 */
public class MutableCell extends Cell{

    public MutableCell(List<Content> contentsList) {
        super(contentsList);
    }

    public MutableCell(Content content) {
        super(content);
    }

    @Override
    public void setContent(Content content) {
        contents.put(content.getTag(),content);
    }

    @Override
    public void setValue(Object value, String contentTag) throws ImmutableContentValueException, NoFindContentbyTagException {
        Content content = contents.get(contentTag);
        if (content != null) {
            content.setValue(value);
        } else {
            throw new NoFindContentbyTagException("Cannot find content for the tag: " + contentTag);
        }
    }




}
