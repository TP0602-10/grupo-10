package ar.fiuba.tdd.grupo10.nikoligames.game.inshi;

import java.util.List;

public class BoardValue {

    protected List<Integer> position;
    protected String value;

    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(List<Integer> position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
