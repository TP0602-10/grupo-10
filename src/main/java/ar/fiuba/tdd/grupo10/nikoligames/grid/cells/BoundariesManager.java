package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.types.*;

public class BoundariesManager {
    private NeighbourContainer topBorder;
    private NeighbourContainer rightBorder;
    private NeighbourContainer bottomBorder;
    private NeighbourContainer leftBorder;

    private NeighbourContainer topLeftCorner;
    private NeighbourContainer topRightCorner;
    private NeighbourContainer bottomRightCorner;
    private NeighbourContainer bottomLeftCorner;

    public NeighbourType getBoundaryFrom(Container otherBoundary) {
        NeighbourContainer[] boundaries = {
                topLeftCorner,
                topBorder,
                topRightCorner,
                rightBorder,
                bottomRightCorner,
                bottomBorder,
                bottomLeftCorner,
                leftBorder
        };
        for (NeighbourContainer boundary : boundaries) {
            if ( boundary != null ) {
                if (boundary.getNeighbourContainer() == otherBoundary) {
                    return boundary.getNeighbourType();
                }
            }
        }
        return new InvalidNeighbour();
    }

    private Container getNeighbour(NeighbourContainer neighbourContainer) {
        return (neighbourContainer != null) ? neighbourContainer.getNeighbourContainer() : null;
    }

    public Container getTopBorder() {
        return getNeighbour(topBorder);
    }

    public void setTopBorder(Container topBorder) {
        this.topBorder = new NeighbourContainer(topBorder, new TopNeighbour());
    }

    public Container getRightBorder() {
        return getNeighbour(rightBorder);
    }

    public void setRightBorder(Container rightBorder) {
        this.rightBorder = new NeighbourContainer(rightBorder, new RightNeighbour());
    }

    public Container getBottomBorder() {
        return getNeighbour(bottomBorder);
    }

    public void setBottomBorder(Container bottomBorder) {
        this.bottomBorder = new NeighbourContainer(bottomBorder, new BottomNeighbour());
    }

    public Container getLeftBorder() {
        return getNeighbour(leftBorder);
    }

    public void setLeftBorder(Container leftBorder) {
        this.leftBorder = new NeighbourContainer(leftBorder, new LeftNeighbour());
    }

    public Container getTopLeftCorner() {
        return getNeighbour(topLeftCorner);
    }

    public void setTopLeftCorner(Container topLeftCorner) {
        this.topLeftCorner = new NeighbourContainer(topLeftCorner, new TopLeftNeighbour());
    }

    public Container getBottomRightCorner() {
        return getNeighbour(bottomRightCorner);
    }

    public void setBottomRightCorner(Container bottomRightCorner) {
        this.bottomRightCorner = new NeighbourContainer(bottomRightCorner, new BottomRightNeighbour());
    }

    public Container getTopRightCorner() {
        return getNeighbour(topRightCorner);
    }

    public void setTopRightCorner(Container topRightCorner) {
        this.topRightCorner = new NeighbourContainer(topRightCorner, new TopRightNeighbour());
    }

    public Container getBottomLeftCorner() {
        return getNeighbour(bottomLeftCorner);
    }

    public void setBottomLeftCorner(Container bottomLeftCorner) {
        this.bottomLeftCorner = new NeighbourContainer(bottomLeftCorner, new BottomLeftNeighbour());
    }
}
