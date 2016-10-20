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

    @Override
    public void paintComponent(Graphics graphics) {

        Cell cell = (Cell) getValue();
        drawOvals(graphics, cell);

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

    private void drawOvals(Graphics graphics, Cell cell) {
        if (cell.getLimitAt(NeighbourPosition.TOP_LEFT) != null && cell.getLimitAt(NeighbourPosition.TOP_LEFT).getValue(NUMBER) != null) {
            graphics.drawOval(-4, -4, 20, 20);
            graphics.drawString(String.valueOf(cell.getLimitAt(NeighbourPosition.TOP_LEFT).getValue(NUMBER)), 0, 10);
        }

        if (cell.getLimitAt(NeighbourPosition.TOP_RIGHT) != null && cell.getLimitAt(NeighbourPosition.TOP_RIGHT).getValue(NUMBER) != null) {
            graphics.drawOval(getWidth() + 4, -4, 20, 20);
            graphics.drawString(String.valueOf(cell.getLimitAt(NeighbourPosition.TOP_RIGHT).getValue(NUMBER)), getWidth() - 6, 10);
        }

        if (cell.getLimitAt(NeighbourPosition.BOTTOM_RIGHT) != null && cell.getLimitAt(NeighbourPosition.BOTTOM_RIGHT).getValue(NUMBER) != null) {
            graphics.drawOval(getWidth() + 4, getHeight() + 4, 20, 20);
            graphics.drawString(String.valueOf(cell.getLimitAt(NeighbourPosition.BOTTOM_RIGHT).getValue(NUMBER)), getWidth() - 6, getHeight() - 6);
        }

        if (cell.getLimitAt(NeighbourPosition.BOTTOM_LEFT) != null && cell.getLimitAt(NeighbourPosition.BOTTOM_LEFT).getValue(NUMBER) != null) {
            graphics.drawOval(-4, getHeight() + 4, 20, 20);
            graphics.drawString(String.valueOf(cell.getLimitAt(NeighbourPosition.BOTTOM_LEFT).getValue(NUMBER)), 0, getHeight() - 6);
        }
    }

}
