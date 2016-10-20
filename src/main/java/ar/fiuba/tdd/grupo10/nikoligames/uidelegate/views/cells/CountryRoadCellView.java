package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.*;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.*;

public class CountryRoadCellView extends CellView {

    public CountryRoadCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {

        Cell cell = (Cell) getValue();
        Content content = cell.getContent(NUMBER);
        if (content != null) {
            graphics.drawString(String.valueOf(content.getValue()), (this.getWidth() / 2) - 4, (this.getHeight() / 2) + 4);
        }

        setBorders(cell);
        drawBorders(graphics);

        drawLines(cell, graphics);
    }

    private void drawLines(Cell cell, Graphics graphics) {
        Line line = (Line) cell.getValue(LINE);
        if (line != null && line instanceof VerticalLine) {
            graphics.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        } else if (line != null && line instanceof HorizontalLine) {
            graphics.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        } else if (line != null && line instanceof FromTopToLeftLine) {
            drawTopLeftLine(graphics);
        } else if (line != null && line instanceof FromBottomToLeftLine) {
            drawBottomLeftLine(graphics);
        } else if (line != null && line instanceof FromTopToRightLine) {
            drawTopRightLine(graphics);
        } else if (line != null && line instanceof FromBottomToRightLine) {
            drawBottomRightLine(graphics);
        }
    }

    private void drawTopLeftLine(Graphics graphics) {
        drawTopLine(graphics);
        graphics.drawLine(0, this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
    }

    private void drawBottomLeftLine(Graphics graphics) {
        drawBottomLine(graphics);
        graphics.drawLine(0, this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
    }

    private void drawTopRightLine(Graphics graphics) {
        drawTopLine(graphics);
        graphics.drawLine(this.getWidth(), this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
    }

    private void drawBottomRightLine(Graphics graphics) {
        drawBottomLine(graphics);
        graphics.drawLine(this.getWidth(), this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
    }

    private void drawTopLine(Graphics graphics) {
        graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, 0);
    }

    private void drawBottomLine(Graphics graphics) {
        graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, this.getHeight());
    }
}
