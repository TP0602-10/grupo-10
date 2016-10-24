package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

import java.util.List;

public class CellLimit {

    private String container;
    private List<ContentStructure> contents;
    private String neighbourPosition;
    private int index;


    public int getIndex() {
        return index;
    }

    public String getContainer() {
        return container;
    }

    public List<ContentStructure> getContents() {
        return contents;
    }

    public String getNeighbourPosition() {
        return neighbourPosition;
    }
}
