package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.NullValue;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.BORDER;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.NUMBER;

public class SlitherlinkCellView extends CellView {

    public SlitherlinkCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Cell cell = (Cell) getValue();
        drawNumber(cell, graphics);
        drawBorders(cell, graphics);
        drawPoints(graphics);
    }

    private void drawNumber(Cell cell, Graphics graphics) {

        Content content = cell.getContent(NUMBER);
        if (content != null) {
            String numberValue = String.valueOf(content.getValue());
            graphics.drawString(numberValue, (this.getWidth() / 2) - 4, (this.getHeight() / 2) + 4);
        }
    }

    private boolean hasBorder(Cell cell, NeighbourPosition neighbourPosition) {
        Container container = cell.getLimitAt(neighbourPosition);
        return container != null && container.getValue(BORDER) != null
                && !(container.getValue(BORDER) instanceof NullValue);
    }

    @Override
    void drawBorders(Cell cell, Graphics graphics) {
        checkAndRenderBottomBorder(cell, graphics);
        checkAndRenderRightBorder(cell, graphics);
        if (hasBorder(cell, NeighbourPosition.LEFT)) {
            drawLeftBorder(graphics);
        }
        if (hasBorder(cell, NeighbourPosition.TOP)) {
            drawTopBorder(graphics);
        }
    }

    private void checkAndRenderBottomBorder(Cell cell, Graphics graphics) {
        if (hasBorder(cell, NeighbourPosition.BOTTOM)) {
            drawBottomBorder(graphics);
        }
    }

    private void checkAndRenderRightBorder(Cell cell, Graphics graphics) {
        if (hasBorder(cell, NeighbourPosition.RIGHT)) {
            drawRightBorder(graphics);
        }
    }

    private void drawPoints(Graphics graphics) {
        graphics.fillOval(0, 1, 1, 5);
        /*graphics.fillOval(this.getY(),0,5,5);
        graphics.fillOval(0,this.getX(),5,5);
        graphics.fillOval(this.getX(),0,5,5);*/
    }
}
