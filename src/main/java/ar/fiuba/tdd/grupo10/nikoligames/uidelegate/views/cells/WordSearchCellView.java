package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.*;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.helpers.DrawingHelper;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.LETTER;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.LINE;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.NUMBER;

public class WordSearchCellView extends CellView {

    public WordSearchCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Cell cell = (Cell) getValue();
        drawLetter(cell, graphics);
        drawLines(cell, graphics);
    }

    private void drawLetter(Cell cell, Graphics graphics) {
        Content content = cell.getContent(LETTER);
        DrawingHelper.drawCentralChar(content, graphics, this.getWidth(), this.getHeight());
    }

    private void drawLines(Cell cell, Graphics graphics) {
        Line line = (Line) cell.getValue(LINE);
        if (line != null && line instanceof VerticalLine) {
            DrawingHelper.drawVerticalLine(graphics, this.getWidth(), this.getHeight());
        } else if (line != null && line instanceof HorizontalLine) {
            DrawingHelper.drawHorizontalLine(graphics, this.getWidth(), this.getHeight());
        } else if (line != null && line instanceof FromBottomLeftToTopRightDiagonal) {
            graphics.drawLine(0, this.getHeight(), this.getWidth(), 0);
        } else if (line != null && line instanceof FromTopLeftToBottomRightDiagonal) {
            graphics.drawLine(0, 0, this.getWidth(), this.getHeight());
        }
    }

}
