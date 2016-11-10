package ar.fiuba.tdd.grupo10.nikoligames.act2;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.GameBuilderErrorException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.wrappers.SlitherlinkValue;
import org.junit.Test;

public class SlitherlinkTest extends GameTest {

    @Override
    protected void initializeProperties() {
        this.gameEnum = GameEnum.SLITHERLINK;
        this.boardPath = "src/test/resources/act2/slitherlink_board.json";
        this.outputPath = "src/test/resources/act2/slitherlink_output.json";
        this.playsPath = "src/test/resources/act2/slitherlink_play.json";
    }

    @Override
    protected String getValueFromCell(Cell cell) {
        String value = "";
        Boolean[] booleanValues = (Boolean[])cell.getValue(this.gameEnum.getMutableContentTag());

        if (booleanValues != null && booleanValues.length != 0) {

            for (int i = 0 ; i < booleanValues.length ; i++) {
                value += String.valueOf(booleanValues[i] + " ");
            }
        }
        return value;
    }

    @Override
    protected void setValueInCell(Cell cell, String value) {

        Boolean[] booleanValue;
        String[] stringBoolean = value.split(",");

        if (stringBoolean.length == 0) {
            booleanValue = new Boolean[] {Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE};
        } else {
            Boolean top = Boolean.valueOf(stringBoolean[0]);
            Boolean bottom = Boolean.valueOf(stringBoolean[1]);
            Boolean left = Boolean.valueOf(stringBoolean[2]);
            Boolean right = Boolean.valueOf(stringBoolean[3]);
            booleanValue = new Boolean[]{top, bottom, left, right};
        }

        SlitherlinkValue slitherlinkValue = new SlitherlinkValue(booleanValue);
        slitherlinkValue.setValueInCell(cell);
    }

    @Test
    public void gameCountryRoad() throws GameBuilderErrorException {
        playTheGame();
    }

}