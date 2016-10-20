package ar.fiuba.tdd.grupo10.nikoligames.json.structures;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRule;

import java.util.List;

public class GameStructure {

    private BoardStructure board;
    private List<Cell> initialBoard;
    private List<RuleStructure> rules;

    public List<RuleStructure> getRules() {
        return rules;
    }

    public List<Cell> getInitialBoard() {
        return initialBoard;
    }

    public BoardStructure getBoard() {
        return board;
    }
}
