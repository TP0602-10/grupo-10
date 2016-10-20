package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

public class ContentStructure {
    private String type;
    private String tag;
    private ValueStructure value;

    public ValueStructure getValue() {
        return value;
    }

    public String getTag() {
        return tag;
    }

    public String getType() {
        return type;
    }
}
