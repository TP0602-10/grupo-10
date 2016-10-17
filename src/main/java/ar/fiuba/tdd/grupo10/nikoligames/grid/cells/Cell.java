package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic structure of the cell.
 */
public class Cell extends Container {

    // TODO: 16/10/16 Set borders and corners when new neighbour is setted.
    private Container leftNeighbour;
    private Container topNeighbour;
    private Container rightNeighbour;
    private Container bottomNeighbour;

    private List<Container> borders = new ArrayList<>();
    private List<Container> corners = new ArrayList<>();

    public Cell(ContainerState state) {
        super(state);
    }

    public Container getLeftNeighbour() {
        return this.leftNeighbour;
    }

    public void setLeftNeighbour(Container leftNeighbour) {
        this.leftNeighbour = leftNeighbour;
    }

    public Container getTopNeighbour() {
        return this.topNeighbour;
    }

    public void setTopNeighbour(Container topNeighbour) {
        this.topNeighbour = topNeighbour;
    }

    public Container getRightNeighbour() {
        return this.rightNeighbour;
    }

    public void setRightNeighbour(Container rightNeighbour) {
        this.rightNeighbour = rightNeighbour;
    }

    public Container getBottomNeighbour() {
        return this.bottomNeighbour;
    }

    public void setBottomNeighbour(Container bottomNeighbour) {
        this.bottomNeighbour = bottomNeighbour;
    }

    public List<Container> getBorders() {
        return this.borders;
    }

    public List<Container> getCorners() {
        return this.corners;
    }

}
