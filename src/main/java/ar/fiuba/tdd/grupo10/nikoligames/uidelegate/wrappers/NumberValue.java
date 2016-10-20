package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


public class NumberValue implements PossibleValue {

    private Integer value;

    public NumberValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isEquivalentTo(Object otherValue) {
        if (otherValue == null) {
            return this.value == null;
        } else {
            return otherValue.equals(this.value);
        }
    }

}
