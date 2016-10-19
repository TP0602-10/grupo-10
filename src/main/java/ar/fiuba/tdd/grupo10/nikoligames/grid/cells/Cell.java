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
    private NeighbourContainer topLeftNeighbour;
    private NeighbourContainer topRightNeighbour;
    private NeighbourContainer bottomRightNeighbour;
    private NeighbourContainer bottomLeftNeighbour;

    private List<Container> borders = new ArrayList<>();
    private List<Container> corners = new ArrayList<>();

    public Cell(ContainerState state) {
        super(state);
    }

    private Container getNeighbour( NeighbourContainer neighbourContainer ) {
        return (neighbourContainer != null) ? neighbourContainer.getNeighbourContainer() : null;
    }

    public NeighbourContainer getTopLeftNeighbour() {
        return topLeftNeighbour;
    }

    public void setTopLeftNeighbour(NeighbourContainer topLeftNeighbour) {
        this.topLeftNeighbour = topLeftNeighbour;
    }

    public NeighbourContainer getTopRightNeighbour() {
        return topRightNeighbour;
    }

    public void setTopRightNeighbour(NeighbourContainer topRightNeighbour) {
        this.topRightNeighbour = topRightNeighbour;
    }

    public NeighbourContainer getBottomRightNeighbour() {
        return bottomRightNeighbour;
    }

    public void setBottomRightNeighbour(NeighbourContainer bottomRightNeighbour) {
        this.bottomRightNeighbour = bottomRightNeighbour;
    }

    public NeighbourContainer getBottomLeftNeighbour() {
        return bottomLeftNeighbour;
    }

    public void setBottomLeftNeighbour(NeighbourContainer bottomLeftNeighbour) {
        this.bottomLeftNeighbour = bottomLeftNeighbour;
    }

    public Container getLeftNeighbour() {
        return getNeighbour(leftNeighbour);
    }

    public void setLeftNeighbour(Container leftNeighbour) {
        this.leftNeighbour = new NeighbourContainer(leftNeighbour,new LeftNeighbour());
    }

    public Container getTopNeighbour() {
        return getNeighbour(topNeighbour);
    }

    public void setTopNeighbour(Container topNeighbour) {
        this.topNeighbour = new NeighbourContainer(topNeighbour,new TopNeighbour());
    }

    public Container getRightNeighbour() {
        return getNeighbour(rightNeighbour);
    }

    public void setRightNeighbour(Container rightNeighbour) {
        this.rightNeighbour = new NeighbourContainer(rightNeighbour,new RightNeighbour());
    }

    public Container getBottomNeighbour() {
        return getNeighbour(bottomNeighbour);
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

    public NeighbourType getNeighbourFrom(Cell otherCell) {
        NeighbourContainer[] neighbours = {
                topLeftNeighbour,
                topNeighbour,
                topRightNeighbour,
                rightNeighbour,
                bottomRightNeighbour,
                bottomNeighbour,
                bottomLeftNeighbour,
                leftNeighbour
        };
        for (NeighbourContainer neighbour : neighbours) {
            if ( neighbour != null ) {
                Cell neighbourCell = (Cell) neighbour.getNeighbourContainer();
                if (neighbourCell == otherCell) {
                    return neighbour.getNeighbourType();
                }
            }
        }
        return new InvalidNeighbour();
    }

}
