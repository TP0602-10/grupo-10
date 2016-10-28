package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContainerException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.util.List;

public class Container {
    private ContainerState state;

    public Container(ContainerState state) {
        this.state = state;
    }

    public List<Content> getAllContent() {
        return state.getAllContent();
    }

    public void setContent(Content content) throws ImmutableContainerException {
        state.setContent(content);
    }

    public void setValue(Object value, String contentTag) throws ImmutableContentValueException,
            ImmutableContainerException,
            NoFindContentbyTagException {
        state.setValue(value, contentTag);
    }

    public void setValue(Object value) throws ImmutableContentValueException,ImmutableContainerException,NoFindContentbyTagException {
        state.setValue(value);
    }

    public Content getContent(String tag) {
        return state.getContent(tag);
    }

    public Content getContent() throws NoFindContentbyTagException {
        return state.getContent();
    }

    public List<Content> getContents(List<String> tags) {
        return state.getContents(tags);
    }

    public boolean isContentEditable() {
        return state.isContentEditable();
    }

    public boolean isEmpty() {
        return state.isEmpty();
    }

    public boolean isCompletelyFilled() {
        return state.isCompletelyFilled();
    }

    public Object getValue(String tag) throws NoFindContentbyTagException {
        return state.getValue(tag);
    }

    public Object getValue() throws NoFindContentbyTagException {
        return state.getValue();
    }
}
