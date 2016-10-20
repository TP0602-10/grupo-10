package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.FromBottomLeftToTopRightDiagonal;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.FromTopLeftToBottomRightDiagonal;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.*;

public class GokigenCellView extends CellView {

    public GokigenCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {

        Cell cell = (Cell) getValue();
        String cellValue = String.valueOf(cell.getValue());

        graphics.drawOval(-4, -4, 20, 20);
        graphics.drawString(cellValue, 0, 10);

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

}
