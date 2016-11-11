package ar.fiuba.tdd.grupo10.nikoligames.act2;

import ar.fiuba.tdd.grupo10.nikoligames.GamesBuilder;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.FileReadException;
import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameBuilderErrorException;
import ar.fiuba.tdd.grupo10.nikoligames.game.inshi.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GameRulesObserver;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static ar.fiuba.tdd.grupo10.nikoligames.helpers.FileHelper.getFileContent;
import static ar.fiuba.tdd.grupo10.nikoligames.helpers.FileHelper.writeToFile;

public abstract class GameTest implements GameRulesObserver {

    protected GameEnum gameEnum;
    protected static final String EXTERN_MAP_ROW = "row";
    protected static final String EXTERN_MAP_COL = "col";
    protected static final String EXTERN_MAP_VALUE = "val";

    protected List<Map<String, String>> plays;
    protected Map<String, String> playsInvalidity;
    protected String boardValidity = "valid";

    protected String boardPath;
    protected String outputPath;
    protected String playsPath;

    protected abstract void initializeProperties();

    private void loadPlays() throws FileReadException {

        GameInput countryRoadInput = (GameInput) getFileContent(playsPath, GameInput.class);
        plays = new ArrayList<>();
        for (InputPlay play : countryRoadInput.getPlays()) {
            Map<String, String> cellPlay = new HashMap<>();
            cellPlay.put(EXTERN_MAP_VALUE, play.getValue());
            cellPlay.put(EXTERN_MAP_ROW, String.valueOf(play.getPosition().get(0) - 1));
            cellPlay.put(EXTERN_MAP_COL, String.valueOf(play.getPosition().get(1) - 1));
            plays.add(cellPlay);
        }
    }

    @Before
    public void setUp() throws FileReadException {
        initializeProperties();
        loadPlays();
        playsInvalidity = new HashMap<>();
    }

    @Test
    public void createGrid() throws GameBuilderErrorException {
        Grid gameGrid = GamesBuilder.createUsingJson(boardPath);
        Assert.assertNotNull(gameGrid);
        Assert.assertFalse(gameGrid.isComplete());
    }

    protected abstract String getValueFromCell(Cell cell);

    private OutputBoard createOutputBoard(Grid grid) {
        List<BoardValue> boardValues = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < grid.getCells().size(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < grid.getCells().size(); columnIndex++) {

                Cell cell = grid.getCellAt(rowIndex, columnIndex);

                List<Integer> position = new ArrayList<>(Arrays.asList(rowIndex + 1, columnIndex + 1));
                String value  = getValueFromCell(cell);

                BoardValue boardValue = new BoardValue();
                boardValue.setPosition(position);
                boardValue.setValue(value);
                boardValues.add(boardValue);
            }
        }
        return new OutputBoard(boardValues, boardValidity);
    }

    protected abstract void setValueInCell(Cell cell, String value);

    protected void playTheGame()throws GameBuilderErrorException  {

        Grid grid = GamesBuilder.createUsingJson(boardPath);
        grid.addRuleObserver(this);
        Assert.assertFalse(grid.isComplete());
        Integer playNumber = 1;
        List<OutputPlay> outputPlays = new ArrayList<>();
        for (Map<String, String> play : plays) {

            String value = play.get(EXTERN_MAP_VALUE);
            int row = Integer.parseInt(play.get(EXTERN_MAP_ROW));
            int col = Integer.parseInt(play.get(EXTERN_MAP_COL));

            Cell cell = grid.getCellAt(row, col);
            setValueInCell(cell, value);

            Map<String, Object> extras = new HashMap<>();
            extras.put("playNumber", playNumber.toString());

            grid.notifyGridUpdated(extras);
            outputPlays.add(new OutputPlay(playNumber));
            playNumber++;
        }

        setPlaysValidity(outputPlays);
        writeOutput(grid, outputPlays);
    }

    private void writeOutput(Grid gameGrid, List<OutputPlay> outputPlays) {
        GameOutput output = new GameOutput();
        output.setBoard(createOutputBoard(gameGrid));
        output.setPlays(outputPlays);
        writeToFile(output, outputPath);
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
