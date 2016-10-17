package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContainerException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.util.List;

public class ImmutableContainer extends ContainerState {
    public ImmutableContainer(List<Content> contentsList) {
        super(contentsList);
    }

    public ImmutableContainer(Content content) {
        super(content);
    }

    @Override
    public void setContent(Content content) throws ImmutableContainerException {
        throw new ImmutableContainerException("Immutable container.");
    }

    @Override
    public void setValue(Object value, String contentTag) throws ImmutableContainerException {
        throw new ImmutableContainerException("Immutable container");
    }

    @Override
    public void setValue(Object value) throws ImmutableContainerException {
        throw new ImmutableContainerException("Immutable container");
    }

    @Override
    public boolean isContentEditable() {
        return false;
    }
}
