package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

import java.util.List;

public class GameStructure {

    private BoardStructure board;
    private List<ContainerStructure> initialBoard;
    private List<RuleStructure> rules;

    public List<RuleStructure> getRules() {
        return rules;
    }

    public List<ContainerStructure> getInitialBoard() {
        return initialBoard;
    }

    public BoardStructure getBoard() {
        return board;
    }
}
