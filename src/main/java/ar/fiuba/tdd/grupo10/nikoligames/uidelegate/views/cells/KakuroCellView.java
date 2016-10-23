package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.MARGIN_CELL;

public class KakuroCellView extends CellView {

    public KakuroCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Cell cell = (Cell) getValue();

        if (cell.isContentEditable()) {
            graphics.setColor(Color.BLUE);
            Content content = cell.getContent("Number");
            if (content != null) {
                Object value = content.getValue() != null ? content.getValue() : "";
                String contentValue = String.valueOf(value);
                graphics.drawString(contentValue, (this.getWidth() / 2) - 5, (this.getHeight() / 2) + 5);
            }
        } else {
            graphics.setColor(Color.black);
        }

        drawLines(cell, graphics);
    }

    private void drawLines(Cell cell, Graphics graphics) {
        drawCompareToRight(cell, graphics);
        drawCompareToBottom(cell, graphics);
    }

    private void drawCompareToRight(Cell cell, Graphics graphics) {
        Content content = cell.getContent("CompareToRight");
        if (content != null) {
            drawDiagonal(graphics);
            graphics.drawString(content.getValue().toString(), (this.getWidth() / 2) + MARGIN_CELL, this.getHeight() / 2);
        } else if (!cell.isContentEditable()) {
            drawTopRectangle(graphics);
        }
    }

    private void drawTopRectangle(Graphics graphics) {
        graphics.fillPolygon(new int[]{0, this.getWidth(), this.getWidth()}, new int[]{0, 0, this.getHeight()}, 3);
    }

    private void drawCompareToBottom(Cell cell, Graphics graphics) {
        Content content = cell.getContent("CompareToDown");
        if (content != null) {
            drawDiagonal(graphics);
            graphics.drawString(content.getValue().toString(), 6, (this.getHeight() / 2) + +MARGIN_CELL);
        } else if (!cell.isContentEditable()) {
            drawBottomRectangle(graphics);
        }
    }

    private void drawBottomRectangle(Graphics graphics) {
        graphics.fillPolygon(new int[]{0, 0, this.getWidth()}, new int[]{0, this.getHeight(), this.getHeight()}, 3);
    }

    private void drawDiagonal(Graphics graphics) {
        graphics.drawLine(this.getWidth(), this.getHeight(), 0, 0);
    }

}
