package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

import java.util.List;

public class GameStructure {

    private BoardStructure board;
    private List<ContainerStructure> initialBoard;
    private List<RuleStructure> rules;
    private List<RuleStructure> winningRules;
    private List<CellLimit> cellLimits;

    public List<RuleStructure> getRules() {
        return rules;
    }

    public List<RuleStructure> getWinningRules() {
        return winningRules;
    }

    public List<ContainerStructure> getInitialBoard() {
        return initialBoard;
    }

    public BoardStructure getBoard() {
        return board;
    }

    public List<CellLimit> getCellLimits() {
        return cellLimits;
    }
}
