package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic structure of the cell.
 */
public class Cell extends Container {

    // TODO: 16/10/16 Set borders and corners when new neighbour is setted.
    private NeighbourContainer leftNeighbour;
    private NeighbourContainer topNeighbour;
    private NeighbourContainer rightNeighbour;
    private NeighbourContainer bottomNeighbour;

    private List<Container> borders = new ArrayList<>();
    private List<Container> corners = new ArrayList<>();

    public Cell(ContainerState state) {
        super(state);
    }

    public Container getLeftNeighbour() {
        return leftNeighbour.getNeighbourContainer();
    }

    public void setLeftNeighbour(Container leftNeighbour) {
        this.leftNeighbour = new NeighbourContainer(leftNeighbour,new LeftNeighbour());
    }

    public Container getTopNeighbour() {
        return topNeighbour.getNeighbourContainer();
    }

    public void setTopNeighbour(Container topNeighbour) {
        this.topNeighbour = new NeighbourContainer(topNeighbour,new TopNeighbour());
    }

    public Container getRightNeighbour() {
        return this.rightNeighbour.getNeighbourContainer();
    }

    public void setRightNeighbour(Container rightNeighbour) {
        this.rightNeighbour = new NeighbourContainer(rightNeighbour,new RightNeighbour());
    }

    public Container getBottomNeighbour() {
        return this.bottomNeighbour.getNeighbourContainer();
    }

    public void setBottomNeighbour(Container bottomNeighbour) {
        this.bottomNeighbour = new NeighbourContainer(bottomNeighbour,new BottomNeighbour());
    }

    public List<Container> getBorders() {
        return this.borders;
    }

    public List<Container> getCorners() {
        return this.corners;
    }

    public NeighbourType getNeighbourFrom(Cell otherCell ) {
        NeighbourContainer[] neighbours = {
                topNeighbour,
                bottomNeighbour,
                leftNeighbour,
                rightNeighbour
        };
        for (NeighbourContainer neighbour : neighbours) {
            Cell neighbourCell = (Cell)neighbour.getNeighbourContainer();
            if (neighbourCell == otherCell) {
                return neighbour.getNeighbourType();
            }
        }
        return new InvalidNeighbour();
    }

}
