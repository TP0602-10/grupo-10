package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

public class ValueStructure {

    public ValueStructure(String type, String value) {
        this.value = value;
        this.type = type;
    }

    private String type;
    private String value;

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
