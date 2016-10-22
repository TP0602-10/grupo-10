package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.*;

import java.awt.*;

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
    }

    private void drawNumber(Cell cell, Graphics graphics) {
        if (cell.getValue(NUMBER) != null) {
            String cellValue = (String) cell.getValue(NUMBER);
            cellValue.toString();
            graphics.drawString(cellValue, (this.getWidth() / 2) - 4, (this.getHeight() / 2) + 4);
        }
    }
}
