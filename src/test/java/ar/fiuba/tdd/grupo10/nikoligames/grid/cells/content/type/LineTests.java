package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.type;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.FromTopToLeftLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.HorizontalLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.VerticalLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.NeighbourType;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.TopNeighbour;
import org.junit.Assert;
import org.junit.Test;

public class LineTests {
    @Test
    public void twoNeighbourLinesAreContinous() {
        Line vertical = new VerticalLine("");
        Line topLeft = new FromTopToLeftLine("");

        NeighbourType topNeighbour = new TopNeighbour();

        Assert.assertTrue( topNeighbour.isValid(topLeft,vertical) );
        Assert.assertFalse( topNeighbour.isValid(vertical,topLeft) );
    }

    @Test
    public void twoNeighbourLinesArentContinuos() {
        Line vertical = new VerticalLine("");
        Line horizontal = new HorizontalLine("");

        NeighbourType neighbour = new TopNeighbour();

        Assert.assertFalse( neighbour.isValid(horizontal,vertical) );
        Assert.assertFalse( neighbour.isValid(vertical,horizontal) );
    }

}
