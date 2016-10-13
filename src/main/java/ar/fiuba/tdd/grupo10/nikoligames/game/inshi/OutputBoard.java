package ar.fiuba.tdd.grupo10.nikoligames.game.inshi;

import java.util.List;

public class OutputBoard extends Board {

    public OutputBoard(List<BoardValue> values, String status) {
        super.setValues(values);
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
