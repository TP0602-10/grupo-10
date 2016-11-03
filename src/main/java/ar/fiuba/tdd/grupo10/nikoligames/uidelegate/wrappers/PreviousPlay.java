package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


public class PreviousPlay {

    private Integer row;
    private Integer column;
    private Object value;

    public PreviousPlay(Integer row, Integer column, Object value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }

}
