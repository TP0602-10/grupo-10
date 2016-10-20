package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

public class RuleStructure {

    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private IteratorStructure iterator; // indices de las celdas
    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private String explanation;
    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private OperationStructure operation;
    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private ConditionStructure condition;
    @SuppressWarnings("UWF_UNWRITTEN_FIELD")
    private String type;

    public IteratorStructure getIterator() {
        return iterator;
    }

    public String getExplanation() {
        return explanation;
    }

    public OperationStructure getOperation() {
        return operation;
    }

    public ConditionStructure getCondition() {
        return condition;
    }

    public String getType() {
        return type;
    }
}
