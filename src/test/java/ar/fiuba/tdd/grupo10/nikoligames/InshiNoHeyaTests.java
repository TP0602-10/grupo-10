package ar.fiuba.tdd.grupo10.nikoligames;


import ar.fiuba.tdd.grupo10.nikoligames.game.inshi.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static ar.fiuba.tdd.grupo10.nikoligames.helpers.FileHelper.getFileContent;
import static ar.fiuba.tdd.grupo10.nikoligames.helpers.FileHelper.writeToFile;

public class InshiNoHeyaTests {

    private List<Map<String, Integer>> theGoalsValues;
    private boolean usePositionToIndex = true;
    private List<Map<String, Integer>> solution;
    private List<Map<String, Integer>> plays;

    private void createSolution() {
        int[] solutionArray = {
                3, 4, 1, 5, 2,
                2, 1, 3, 4, 5,
                5, 3, 2, 1, 4,
                1, 5, 4, 2, 3,
                4, 2, 5, 3, 1
        };
        solution = new ArrayList<>();
        int index = 0;
        for (int solValue : solutionArray) {
            Map<String, Integer> cellSolution = new HashMap<>();
            cellSolution.put(InshiNoHeyaFactory.EXTERN_MAP_VALUE, solValue);
            int[] rowColPostion = InshiNoHeyaFactory.getPositionFromIndex(index);
            cellSolution.put(InshiNoHeyaFactory.EXTERN_MAP_ROW, rowColPostion[0]);
            cellSolution.put(InshiNoHeyaFactory.EXTERN_MAP_COL, rowColPostion[1]);
            solution.add(cellSolution);
            index++;
        }

    }

    private void loadBoard() {
        theGoalsValues = new ArrayList<>();
        InputBoard initialBoard = (InputBoard) getFileContent("src/test/resources/inshi_board.json", InputBoard.class);
        for (BoardValue boardValue : initialBoard.getBoard().getValues()) {
            int row = boardValue.getPosition().get(0);
            int col = boardValue.getPosition().get(1);
            int value = Integer.valueOf(boardValue.getValue());
            Map<String, Integer> goalMap = new HashMap<>();
            goalMap.put(InshiNoHeyaFactory.EXTERN_MAP_ROW, row);
            goalMap.put(InshiNoHeyaFactory.EXTERN_MAP_COL, col);
            goalMap.put(InshiNoHeyaFactory.EXTERN_MAP_VALUE, value);
            theGoalsValues.add(goalMap);
        }
    }

    private void loadPlays() {
        InshiNoHeyaInput inshiNoHeyaInput = (InshiNoHeyaInput) getFileContent("src/test/resources/inshi_play.json", InshiNoHeyaInput.class);
        plays = new ArrayList<>();
        for (InputPlay play : inshiNoHeyaInput.getPlays()) {
            Map<String, Integer> cellPlay = new HashMap<>();
            cellPlay.put(InshiNoHeyaFactory.EXTERN_MAP_VALUE, Integer.valueOf(play.getValue()));
            cellPlay.put(InshiNoHeyaFactory.EXTERN_MAP_ROW, play.getPosition().get(0));
            cellPlay.put(InshiNoHeyaFactory.EXTERN_MAP_COL, play.getPosition().get(1));
            plays.add(cellPlay);
        }
    }

    @Before
    public void setUp() {
        loadBoard();
        loadPlays();
    }

    @Test
    public void createGrid() {
        Grid inshiGrid = InshiNoHeyaFactory.createGrid(theGoalsValues, usePositionToIndex);
        Assert.assertNotNull(inshiGrid);
        Assert.assertFalse(inshiGrid.isComplete());
    }

    private String getBoardStatus(boolean value) {
        return value ? "valid" : "invalid";
    }

    private OutputBoard createOutputBoard(Grid grid) {
        OutputBoard board = new OutputBoard();
        List<BoardValue> boardValues = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                Cell cell = grid.getCellAt(rowIndex, columnIndex);
                List<Integer> position = new ArrayList<>(Arrays.asList(rowIndex + 1, columnIndex + 1));
                BoardValue boardValue = new BoardValue();
                boardValue.setPosition(position);
                boardValue.setValue(cell.getValue(InshiNoHeyaFactory.DEFAULT_TAG) != null
                        ? String.valueOf(cell.getValue(InshiNoHeyaFactory.DEFAULT_TAG)) : "");
                boardValues.add(boardValue);
            }
        }
        board.setValues(boardValues);
        //FIXME status should be set according to the actual board status
        board.setStatus(getBoardStatus(true));
        return board;
    }

    @Test
    public void playTheGame() {
        Grid inshiGrid = InshiNoHeyaFactory.createGrid(theGoalsValues, usePositionToIndex);
        Assert.assertFalse(inshiGrid.isComplete());
        int playNumber = 1;
        List<OutputPlay> outputPlays = new ArrayList<>();
        for (Map<String, Integer> play : plays) {
            int value = play.get(InshiNoHeyaFactory.EXTERN_MAP_VALUE);
            int row = play.get(InshiNoHeyaFactory.EXTERN_MAP_ROW);
            int col = play.get(InshiNoHeyaFactory.EXTERN_MAP_COL);
            inshiGrid.getCellAt(row, col).getContent(InshiNoHeyaFactory.DEFAULT_TAG).setValue(value);

            //FIXME boardStatus should be set according to the board status after the play
            outputPlays.add(new OutputPlay(playNumber, getBoardStatus(true)));
            playNumber++;
        }
        InshiNoHeyaOutput output = new InshiNoHeyaOutput();
        output.setBoard(createOutputBoard(inshiGrid));
        output.setPlays(outputPlays);
        writeToFile(output, "src/test/resources/inshi_output.json");
    }
}
