package ar.fiuba.tdd.grupo10.nikoligames.game.inshi;

public class OutputPlay {

    public OutputPlay(Integer number, String boardStatus) {
        this.number = number;
        this.boardStatus = boardStatus;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(String boardStatus) {
        this.boardStatus = boardStatus;
    }

    private Integer number;
    private String boardStatus;
}
