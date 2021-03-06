package ar.fiuba.tdd.grupo10.nikoligames.act2;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameBuilderErrorException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.LineValue;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class GokigenTest extends GameTest {

    @Override
    protected void initializeProperties() {
        this.gameEnum = GameEnum.GOKIGEN_NANAME;
        this.boardPath = "src/test/resources/act2/gokigen_board.json";
        this.outputPath = "src/test/resources/act2/gokigen_output.json";
        this.playsPath = "src/test/resources/act2/gokigen_play.json";
    }

    @Override
    protected String getValueFromCell(Cell cell) {
        LineValue lineValue = (LineValue)cell.getValue(this.gameEnum.getMutableContentTag());
        String value = (lineValue == null) ? "" : String.valueOf(lineValue.getValue().getClass().getSimpleName());
        return value;
    }

    @Override
    protected void  setValueInCell(Cell cell, String value) {
        LineValue lineValue = new LineValue(null);
        try {
            String packageName = "ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.";
            Class lineClass = Class.forName( packageName + value);
            Constructor constructor = lineClass.getConstructor(String.class);
            lineValue = new LineValue((Line) constructor.newInstance(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        cell.setValue(lineValue, gameEnum.getMutableContentTag());
    }

    @Test
    public void gameGokigen() throws GameBuilderErrorException {
        playTheGame();
    }

}
