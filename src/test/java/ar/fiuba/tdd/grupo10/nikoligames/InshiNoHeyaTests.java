package ar.fiuba.tdd.grupo10.nikoligames;


import ar.fiuba.tdd.grupo10.nikoligames.game.inshi.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GameRulesObserver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static ar.fiuba.tdd.grupo10.nikoligames.helpers.FileHelper.getFileContent;
import static ar.fiuba.tdd.grupo10.nikoligames.helpers.FileHelper.writeToFile;

public class InshiNoHeyaTests implements GameRulesObserver {

    private List<Map<String, Integer>> theGoalsValues;
    private boolean usePositionToIndex = true;
    private List<Map<String, Integer>> plays;
    private Map<String, String> playsInvalidity;
    private String boardValidity = "valid";

    private void loadBoard() {
        theGoalsValues = new ArrayList<>();
        InputBoard initialBoard = (InputBoard) getFileContent("src/test/resources/inshi_board.json", InputBoard.class);
        for (BoardValue boardValue : initialBoard.getBoard().getValues()) {
            int row = boardValue.getPosition().get(0);
            int col = boardValue.getPosition().get(1);
            int value = Integer.parseInt(boardValue.getValue());
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
            cellPlay.put(InshiNoHeyaFactory.EXTERN_MAP_ROW, play.getPosition().get(0) - 1);
            cellPlay.put(InshiNoHeyaFactory.EXTERN_MAP_COL, play.getPosition().get(1) - 1);
            plays.add(cellPlay);
        }
    }

    @Before
    public void setUp() {
        loadBoard();
        loadPlays();
        playsInvalidity = new HashMap<>();
    }

    @Test
    public void createGrid() {
        Grid inshiGrid = InshiNoHeyaFactory.createGrid(theGoalsValues, usePositionToIndex);
        Assert.assertNotNull(inshiGrid);
        Assert.assertFalse(inshiGrid.isComplete());
    }

    private OutputBoard createOutputBoard(Grid grid) {
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
        return new OutputBoard(boardValues, boardValidity);
    }


    @Test
    public void playTheGame() {
        Grid inshiGrid = InshiNoHeyaFactory.createGrid(theGoalsValues, usePositionToIndex);
        inshiGrid.addRuleObserver(this);
        Assert.assertFalse(inshiGrid.isComplete());
        Integer playNumber = 1;
        List<OutputPlay> outputPlays = new ArrayList<>();
        for (Map<String, Integer> play : plays) {
            int value = play.get(InshiNoHeyaFactory.EXTERN_MAP_VALUE);
            int row = play.get(InshiNoHeyaFactory.EXTERN_MAP_ROW);
            int col = play.get(InshiNoHeyaFactory.EXTERN_MAP_COL);
            Cell cell = inshiGrid.getCellAt(row, col);
            cell.setValue(value, InshiNoHeyaFactory.DEFAULT_TAG);
            Map<String, Object> extras = new HashMap<>();
            extras.put("playNumber", playNumber.toString());
            inshiGrid.notifyGridUpdated(extras);
            outputPlays.add(new OutputPlay(playNumber));
            playNumber++;
        }
        setPlaysValidity(outputPlays);
        writeOutput(inshiGrid, outputPlays);
    }

    private void writeOutput(Grid inshiGrid, List<OutputPlay> outputPlays) {
        InshiNoHeyaOutput output = new InshiNoHeyaOutput();
        output.setBoard(createOutputBoard(inshiGrid));
        output.setPlays(outputPlays);
        writeToFile(output, "src/test/resources/inshi_output.json");
    }

    private void setPlaysValidity(List<OutputPlay> outputPlays) {
        if (outputPlays != null) {
            for (OutputPlay outputPlay : outputPlays) {
                String invalidity = playsInvalidity.get(outputPlay.getNumber().toString());
                outputPlay.setBoardStatus(invalidity != null ? invalidity : "valid");
            }
        }
    }

    @Override
    public void onRuleUnsatisfied(String message, Map<String, Object> extras) {
        boardValidity = "invalid";
        playsInvalidity.put((String) extras.get("playNumber"), (String) extras.get("validity"));
    }

    @Override
    public void onGameWon(String message) {

    }
}
