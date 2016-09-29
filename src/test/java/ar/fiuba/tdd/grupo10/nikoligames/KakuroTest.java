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
        Grid kakuroGrid = KakuroFactory.createGrid(2);
        Assert.assertEquals(kakuroGrid.getCellAt(0, 0).getContent().getValue(), null);
        Assert.assertEquals(kakuroGrid.getCellAt(0, 2).getContent().getValue(), 16);
        Assert.assertEquals(kakuroGrid.getCellAt(0, 6).getContent().getValue(), 6);
        kakuroGrid.getCellAt(1, 2).setValue(5);
        Assert.assertEquals(kakuroGrid.getCellAt(1, 2).getContent().getValue(), 5);
    }

    @Test
    public void playCompleteKakuroTestGame() {
        Grid kakuroGrid = KakuroFactory.createGrid(2);
        kakuroGrid.getCellAt(1, 1).setValue(9);
        kakuroGrid.getCellAt(1, 2).setValue(7);
        kakuroGrid.getCellAt(1, 5).setValue(3);
        kakuroGrid.getCellAt(1, 6).setValue(1);
        kakuroGrid.getCellAt(2, 1).setValue(8);
        kakuroGrid.getCellAt(2, 2).setValue(9);
        kakuroGrid.getCellAt(2, 3).setValue(6);
        kakuroGrid.getCellAt(2, 5).setValue(9);
        kakuroGrid.getCellAt(2, 6).setValue(5);
        kakuroGrid.getCellAt(2, 7).setValue(7);
        kakuroGrid.getCellAt(2, 8).setValue(9);
        Assert.assertEquals(kakuroGrid.isComplete(), Boolean.FALSE);
        kakuroGrid.getCellAt(4, 8).setValue(6);
    }
}
