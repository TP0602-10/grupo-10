package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContainerException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.util.List;

public class MutableContainer extends ContainerState {
    public MutableContainer(List<Content> contentsList) {
        super(contentsList);
    }

    public MutableContainer(Content content) {
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
    public void setValue(Object value) throws NoFindContentbyTagException, ImmutableContainerException, ImmutableContentValueException {
        if (contents.size() > 1) {
            throw new NoFindContentbyTagException("No specified tag, container contains more than one content.");
        }
        Content content = contents.entrySet().iterator().next().getValue();
        content.setValue(value);
    }

    @Override
    public boolean isContentEditable() {
        return true;
    }
}
