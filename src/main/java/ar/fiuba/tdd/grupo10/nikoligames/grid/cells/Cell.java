package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableCellException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.ImmutableContentValueException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.NoFindContentbyTagException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic structure of the cell.
 */
public abstract class Cell {

    protected Map<String,Content> contents = new HashMap<>();

    public Cell(List<Content> contentsList) {
        for (Content content : contentsList) {
            contents.put(content.getTag(), content);
        }
    }

    public Cell(Content content) {
        this.contents.put(content.getTag(),content);
    }

    public List<Content> getAllContent() {
        return new ArrayList<>(contents.values());
    }

    public abstract void setContent(Content content) throws ImmutableCellException;

    public abstract void setValue(Object value,String contentTag) throws ImmutableContentValueException,
            ImmutableCellException,
            NoFindContentbyTagException;

    public Content getContent(String tag) {
        return contents.get(tag);
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

}
