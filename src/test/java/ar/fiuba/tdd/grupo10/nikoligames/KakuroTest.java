package ar.fiuba.tdd.grupo10.nikoligames;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.helpers.ListHelper;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KakuroTest {

    @Test
    public void createKakuroGame() {
        Grid kakuroGrid = KakuroFactory.createGrid(1);
        Assert.assertNotNull(kakuroGrid);
    }

    @Test
    public void playKakuroGame() {
        Grid kakuroGrid = KakuroFactory.createGrid(1);
        Assert.assertEquals(kakuroGrid.getCellAt(0, 0).getContent().getValue(), null);
        Assert.assertEquals(kakuroGrid.getCellAt(0, 2).getContent().getValue(), 35);
        Assert.assertEquals(kakuroGrid.getCellAt(0, 6).getContent().getValue(), 39);
    }
    @Ignore
    @Test
    public void checkKakuroTestGameRulesComputeOK() {
        Grid testKakuroGrid = KakuroFactory.createGrid(0);

    }
}
