package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContainerException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ContainerState {
    protected Map<String,Content> contents = new HashMap<>();

    public ContainerState(List<Content> contentsList) {
        for (Content content : contentsList) {
            contents.put(content.getTag(), content);
        }
    }

    public ContainerState(Content content) {
        this.contents.put(content.getTag(),content);
    }

    public abstract void setContent(Content content);

    public abstract void setValue(Object value, String contentTag) throws
            ImmutableContentValueException,
            NoFindContentbyTagException;

    public abstract void setValue(Object value) throws
            NoFindContentbyTagException,
            ImmutableContainerException,
            ImmutableContentValueException;

    public abstract boolean isContentEditable();

    public List<Content> getAllContent() {
        return new ArrayList<>(contents.values());
    }

    public Content getContent(String tag) {
        return contents.get(tag);
    }

    public Content getContent() throws NoFindContentbyTagException {
        if (contents.size() > 1) {
            throw new NoFindContentbyTagException("Not specified tag, cell contain more than one content.");
        }
        return contents.entrySet().iterator().next().getValue();
    }

    public List<Content> getContents(List<String> tagsList) {
        List<Content> contentsList = new ArrayList<>();
        for (String tag : tagsList) {
            Content content = contents.get(tag);
            if (content == null) {
                continue;
            }
            contentsList.add(content);
        }
        return contentsList;
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    public boolean isCompletelyFilled() {
        return contents.keySet().stream()
                .filter( k -> contents.get(k).isValueEditable() )
                .allMatch( k -> ! contents.get(k).isEmpty() );
    }

    public Object getValue(String tag) throws NoFindContentbyTagException {
        if (!contents.containsKey(tag)) {
            throw new NoFindContentbyTagException("no find content by tag:" + tag);
        }
        Content content = contents.get(tag);
        return content.getValue();
    }

    public Object getValue() throws NoFindContentbyTagException {
        return this.getContent().getValue();
    }
}
