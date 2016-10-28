package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

import java.util.List;

public class IteratorStructure {
    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private List<Integer> indexes;
    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private String explanation;

    public String getExplanation() {
        return explanation;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }
}
