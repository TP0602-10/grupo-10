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
            Object content = cell.getValue("Number");
            if (content != null) {
                graphics.drawString(content.toString(), (this.getWidth() / 2) - 5, (this.getHeight() / 2) + 5);
            }
        } else {
            graphics.setColor(Color.black);
        }

        drawLines(cell, graphics);
    }

    private void drawLines(Cell cell, Graphics graphics) {

        Content content = cell.getContent("CompareToRight");
        if (content != null) {
            graphics.drawLine(this.getWidth(), this.getHeight(), 0, 0);
            graphics.drawString(content.getValue().toString(), (this.getWidth() / 2) + MARGIN_CELL, this.getHeight() / 2);
        }

        content = cell.getContent("CompareToDown");
        if (content != null) {
            graphics.drawLine(this.getWidth(), this.getHeight(), 0, 0);
            graphics.drawString(content.getValue().toString(), 6, (this.getHeight() / 2) + +MARGIN_CELL);
        }
    }

}
