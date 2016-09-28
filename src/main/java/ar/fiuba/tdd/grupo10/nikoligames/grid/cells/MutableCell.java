package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableCellException;
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
        if (contents.containsKey(contentTag)) {
            Content content = contents.get(contentTag);
            content.setValue(value);
        } else {
            throw new NoFindContentbyTagException("Cannot find content for the tag: " + contentTag);
        }
    }

    @Override
    public void setValue(Object value) throws NoFindContentbyTagException, ImmutableCellException, ImmutableContentValueException {
        if (contents.size() > 1) {
            throw new NoFindContentbyTagException("Not specified tag, cell contain more than one content.");
        }
        Content content = contents.entrySet().iterator().next().getValue();
        content.setValue(value);
    }




}
