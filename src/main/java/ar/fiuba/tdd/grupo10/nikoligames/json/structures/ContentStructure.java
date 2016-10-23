package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

public class ContentStructure {
    public ContentStructure(String type, String tag, ValueStructure value) {
        this.type = type;
        this.tag = tag;
        this.value = value;
    }

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
