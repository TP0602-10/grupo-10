package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

public class CellLimit {

    private String type;
    private String neighbourPosition;
    private String content;
    private String container;
    private int index;
    private String tag;

    public String getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public String getTag() {
        return tag;
    }

    public String getContainer() {
        return container;
    }

    public String getContent() {
        return content;
    }

    public String getNeighbourPosition() {
        return neighbourPosition;
    }
}
