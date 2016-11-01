package ar.fiuba.tdd.grupo10.nikoligames.game;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.FileReadException;
import ar.fiuba.tdd.grupo10.nikoligames.game.inshi.InputBoard;
import ar.fiuba.tdd.grupo10.nikoligames.game.inshi.InshiNoHeyaInput;
import org.junit.Assert;
import org.junit.Test;

import static ar.fiuba.tdd.grupo10.nikoligames.helpers.FileHelper.getFileContent;

public class InshiTest {

    @Test
    public void readFiles() throws FileReadException {
        InputBoard initialBoardAct1 = (InputBoard) getFileContent("src/test/resources/act1/inshi_board.json", InputBoard.class);
        Assert.assertTrue(initialBoardAct1 != null);
        InshiNoHeyaInput inputFileAct1 = (InshiNoHeyaInput) getFileContent("src/test/resources/act1/inshi_play.json",
                InshiNoHeyaInput.class);
        Assert.assertTrue(inputFileAct1 != null);
        InputBoard initialBoardAct2 = (InputBoard) getFileContent("src/test/resources/act2/inshi_board.json",
                InputBoard.class);
        Assert.assertTrue(initialBoardAct2 != null);
        InshiNoHeyaInput inputFileAct2 = (InshiNoHeyaInput) getFileContent("src/test/resources/act2/inshi_play.json", InshiNoHeyaInput.class);
        Assert.assertTrue(inputFileAct2 != null);
    }

}
