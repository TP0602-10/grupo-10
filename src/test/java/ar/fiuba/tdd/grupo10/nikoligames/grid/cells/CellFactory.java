package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.util.List;

public class CellFactory {
    public static Cell newMutableCell(Content content) {
        return new Cell(new MutableContainer(content));
    }

    public static Cell newMutableCell(List<Content> contents) {
        return new Cell(new MutableContainer(contents));
    }

    public static Cell newImmutableCell(Content content) {
        return new Cell(new ImmutableContainer(content));
    }

    public static Cell newImmutableCell(List<Content> contents) {
        return new Cell(new ImmutableContainer(contents));
    }
}
