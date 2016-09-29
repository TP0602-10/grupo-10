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
        kakuroGrid.getCellAt(1, 2).getContent().setValue(5);
        Assert.assertEquals(kakuroGrid.getCellAt(1, 2).getContent().getValue(), 5);
    }

    @Test
    public void playCompleteKakuroTestGame() {
        Grid kakuroGrid = KakuroFactory.createGrid(2);
        kakuroGrid.getCellAt(1, 1).getContent().setValue(9);
        kakuroGrid.getCellAt(1, 2).getContent().setValue(7);
        kakuroGrid.getCellAt(1, 5).getContent().setValue(3);
        kakuroGrid.getCellAt(1, 6).getContent().setValue(1);
        kakuroGrid.getCellAt(2, 1).getContent().setValue(8);
        kakuroGrid.getCellAt(2, 2).getContent().setValue(9);
        kakuroGrid.getCellAt(2, 3).getContent().setValue(6);
        kakuroGrid.getCellAt(2, 5).getContent().setValue(9);
        kakuroGrid.getCellAt(2, 6).getContent().setValue(5);
        kakuroGrid.getCellAt(2, 7).getContent().setValue(7);
        kakuroGrid.getCellAt(2, 8).getContent().setValue(9);
        Assert.assertEquals(kakuroGrid.isComplete(), Boolean.FALSE);
        kakuroGrid.getCellAt(4, 8).getContent().setValue(6);
    }
}
