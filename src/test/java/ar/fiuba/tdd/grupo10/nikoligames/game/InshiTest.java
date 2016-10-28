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
        InputBoard initialBoard = (InputBoard) getFileContent("src/test/resources/inshi_board.json", InputBoard.class);
        Assert.assertTrue(initialBoard != null);
        InshiNoHeyaInput inputFile = (InshiNoHeyaInput) getFileContent("src/test/resources/inshi_play.json", InshiNoHeyaInput.class);
        Assert.assertTrue(inputFile != null);
    }

}
