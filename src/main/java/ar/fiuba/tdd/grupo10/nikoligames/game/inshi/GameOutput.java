package ar.fiuba.tdd.grupo10.nikoligames.game.inshi;

import java.util.List;

public class GameOutput {

    public List<OutputPlay> getPlays() {
        return plays;
    }

    public void setPlays(List<OutputPlay> plays) {
        this.plays = plays;
    }

    private List<OutputPlay> plays;

    public OutputBoard getBoard() {
        return board;
    }

    public void setBoard(OutputBoard board) {
        this.board = board;
    }

    private OutputBoard board;
}
