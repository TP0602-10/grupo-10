package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

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

        if (VERTICAL_LINE.equals(cellValue)) {
            graphics.drawLine(this.getWidth(), 0, 0, this.getHeight());
        } else if (HORIZONTAL_LINE.equals(cellValue)) {
            graphics.drawLine(0, 0, this.getWidth(), this.getHeight());
        }
    }

}
