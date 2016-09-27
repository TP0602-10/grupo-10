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
public class ImmutableCell implements Cell{

    private Map<String,Content> contents;

    public ImmutableCell(List<Content> contentsList) {
        for (Content content : contentsList) {
            contents.put(content.getTag(), content);
        }
    }

    public ImmutableCell(Content content) {
        contents.put(content.getTag(),content);
    }

    public ImmutableCell(){}

    @Override
    public List<Content> getAllContent() {
        return new ArrayList<>(contents.values());
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
    public Content getContent(String tag) {
        return contents.get(tag);
    }

    @Override
    public List<Content> getContents(List<String> tagsList) {
        List<Content> contentsList = new ArrayList<>();
        for (String aTagsList : tagsList) {
            Content content = contents.get(aTagsList);
            if (content == null) continue;
            contentsList.add(content);
        }
        return contentsList;
    }

    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }
}
