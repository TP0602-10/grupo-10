package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

import java.util.List;

public class OperationStructure {
    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private String name;
    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private List<String> contentTags;
    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private String type;

    private CornerStructure corner;

    public String getName() {
        return name;
    }

    public List<String> getContentTags() {
        return contentTags;
    }

    public String getType() {
        return type;
    }

    public CornerStructure getCorner() {
        return corner;
    }
}
