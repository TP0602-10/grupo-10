package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.*;

public class CountryRoadCellView extends CellView {

    public CountryRoadCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {

        Cell cell = (Cell) getValue();
        String cellValue = getTextValue(cell.getValue());
        graphics.drawString(cellValue, (this.getWidth() / 2) - 4, (this.getHeight() / 2) + 4);

        setBorders(cell);
        drawBorders(graphics);

        drawLines(cell, graphics);
    }

    private void drawLines(Cell cell, Graphics graphics) {

        Content content = cell.getContent("LINE");

        if(content != null) {
            // TODO:
            Line line = (Line)content.getValue();

            if (VERTICAL_LINE.equals(line)) {
                graphics.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
            } else if (HORIZONTAL_LINE.equals(line)) {
                graphics.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
            } else if (UPPER_LEFT.equals(line)) {
                drawTopLine(graphics);
                graphics.drawLine(0, this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
            } else if (BOTTOM_LEFT.equals(line)) {
                drawBottomLine(graphics);
                graphics.drawLine(0, this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
            } else if (UPPER_RIGHT.equals(line)) {
                drawTopLine(graphics);
                graphics.drawLine(this.getWidth(), this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
            } else if (BOTTOM_RIGHT.equals(line)) {
                drawBottomLine(graphics);
                graphics.drawLine(this.getWidth(), this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
            }
        }

    }

    private void drawTopLine(Graphics graphics) {
        graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, 0);
    }

    private void drawBottomLine(Graphics graphics) {
        graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, this.getHeight());
    }
}
