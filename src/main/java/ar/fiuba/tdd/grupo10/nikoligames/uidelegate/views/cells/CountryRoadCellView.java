package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.*;

public class CountryRoadCellView extends CellView {

    public CountryRoadCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {

        Cell cell = (Cell) getValue();
        String cellValue = String.valueOf(cell.getValue());

        if (!cellValue.isEmpty() && !cellValue.equals("null")) {
            graphics.drawString(cellValue, (this.getWidth() / 2) - 4, (this.getHeight() / 2) + 4);
        }
        drawLines(cellValue, graphics);

        graphics.fillRect(0, 0, 3, this.getHeight());
        graphics.fillRect(this.getWidth() - 3, 0, 3, this.getHeight());
        graphics.fillRect(0, 0, this.getWidth(), 3);
        graphics.fillRect(0, this.getHeight() - 3, this.getWidth(), this.getHeight());
    }

    private void drawLines(String cellValue, Graphics graphics) {

        if (VERTICAL_LINE.equals(cellValue)) {
            graphics.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        } else if (HORIZONTAL_LINE.equals(cellValue)) {
            graphics.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        } else if (UPPER_LEFT.equals(cellValue)) {
            drawUpperLine(graphics);
            graphics.drawLine(0, this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
        } else if (BOTTOM_LEFT.equals(cellValue)) {
            drawBottomLine(graphics);
            graphics.drawLine(0, this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
        } else if (UPPER_RIGHT.equals(cellValue)) {
            drawUpperLine(graphics);
            graphics.drawLine(this.getWidth(), this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
        } else if (BOTTOM_RIGHT.equals(cellValue)) {
            drawBottomLine(graphics);
            graphics.drawLine(this.getWidth(), this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
        }
    }

    private void drawUpperLine(Graphics graphics) {
        graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, 0);
    }

    private void drawBottomLine(Graphics graphics) {
        graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, this.getHeight());
    }
}
