package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.FromBottomLeftToTopRightDiagonal;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.FromTopLeftToBottomRightDiagonal;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.*;

public class GokigenCellView extends CellView {

    public GokigenCellView() {
        super();
    }

    private static final String OVAL_TAG = "GOALPOINT";

    @Override
    public void paintComponent(Graphics graphics) {

        Cell cell = (Cell) getValue();
        drawOvals(graphics, cell);
        graphics.setColor(Color.black);
        Line line = (Line) cell.getValue(LINE);
        if (line == null) {
            return;
        }
        if (line instanceof FromBottomLeftToTopRightDiagonal) {
            graphics.drawLine(this.getWidth(), 0, 0, this.getHeight());
        } else if (line instanceof FromTopLeftToBottomRightDiagonal) {
            graphics.drawLine(0, 0, this.getWidth(), this.getHeight());
        }
    }

    private boolean hasOval(Cell cell, NeighbourPosition neighbourPosition) {
        return cell.getLimitAt(neighbourPosition) != null
                && cell.getLimitAt(neighbourPosition).getValue(OVAL_TAG) != null;
    }

    private void drawOvals(Graphics graphics, Cell cell) {
        drawTopLeftOval(graphics, cell);
//        drawTopRightOval(graphics, cell);
//        drawBottomLeftOval(graphics, cell);
        if (getRow() == 4 || getColumn() == 4) {
            drawBottomRightOval(graphics, cell);
        }
    }

    private void drawTopLeftOval(Graphics graphics, Cell cell) {
        if (hasOval(cell, NeighbourPosition.TOP_LEFT)) {
            graphics.setColor(Color.red);
            graphics.fillOval(-4, -4, 20, 20);
            graphics.setColor(Color.white);
            graphics.drawString(String.valueOf(cell.getLimitAt(NeighbourPosition.TOP_LEFT)
                    .getValue(OVAL_TAG)), 0, 10);
        }
    }

    private void drawTopRightOval(Graphics graphics, Cell cell) {
        if (hasOval(cell, NeighbourPosition.TOP_RIGHT)) {
            graphics.setColor(Color.red);
            graphics.drawOval(getWidth() + 4, -4, 20, 20);
            graphics.setColor(Color.white);
            graphics.drawString(String.valueOf(cell.getLimitAt(NeighbourPosition.TOP_RIGHT)
                    .getValue(OVAL_TAG)), getWidth() - 6, 10);
        }
    }

    private void drawBottomRightOval(Graphics graphics, Cell cell) {
        if (hasOval(cell, NeighbourPosition.BOTTOM_RIGHT)) {
            graphics.setColor(Color.red);
            graphics.fillOval(getWidth() - 15, getHeight() - 15, 20, 20);
            graphics.setColor(Color.white);
            graphics.drawString(String.valueOf(cell.getLimitAt(NeighbourPosition.BOTTOM_RIGHT)
                    .getValue(OVAL_TAG)), getWidth() - 9, getHeight() - 2);
        }
    }

    private void drawBottomLeftOval(Graphics graphics, Cell cell) {
        if (hasOval(cell, NeighbourPosition.BOTTOM_LEFT)) {
            graphics.drawOval(-4, getHeight() + 4, 20, 20);
            graphics.drawString(String.valueOf(cell.getLimitAt(NeighbourPosition.BOTTOM_LEFT)
                    .getValue(OVAL_TAG)), 0, getHeight() - 6);
        }
    }

}
