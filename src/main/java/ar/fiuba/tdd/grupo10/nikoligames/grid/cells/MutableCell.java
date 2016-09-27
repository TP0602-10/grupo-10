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
public class MutableCell implements Cell{

    private Map<String,Content> contents;

    public MutableCell(List<Content> contentsList) {
        for (Content content : contentsList) {
            contents.put(content.getTag(), content);
        }
    }

    public MutableCell(Content content) {
        contents.put(content.getTag(),content);
    }

    public MutableCell(){}

    @Override
    public List<Content> getAllContent() {
        return new ArrayList<>(contents.values());
    }

    @Override
    public void setContent(Content content) {
        contents.put(content.getTag(),content);
    }

    @Override
    public void setValue(Object value, String contentTag) throws ImmutableContentValueException, NoFindContentbyTagException {
        Content content = contents.get(contentTag);
        if (content != null) content.setValue(value);
        else throw new NoFindContentbyTagException("don't find a content for the tag: " + contentTag);
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
