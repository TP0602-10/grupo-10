package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers;


import java.util.List;

public class MiniSnapshot {

    private List<PreviousPlay> previousPlays;

    public MiniSnapshot(List<PreviousPlay> previousPlays) {
        this.previousPlays = previousPlays;
    }

    public List<PreviousPlay> getPreviousPlays() {
        return previousPlays;
    }

}
