package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.helpers.DrawingHelper;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.NUMBER;

public class TotoroCellView extends CellView {

    public TotoroCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Cell cell = (Cell) getValue();

        this.setBackgroundColor(cell.isContentEditable(),graphics);
        Content content = cell.getContent(NUMBER);
        drawCentralNumber(cell.isContentEditable(), content, graphics);
        drawBorder(graphics);
    }

    private void drawBorder(Graphics graphics) {
        drawRowBorder(graphics);
        drawColumnBorder(graphics);
    }

    private void drawRowBorder(Graphics graphics) {
        if (this.getRow() == 3) {
            DrawingHelper.drawBottomBorder(graphics, this.getWidth(), this.getHeight());
        } else if (this.getRow() == 4) {
            DrawingHelper.drawTopBorder(graphics, this.getWidth());
        }
    }

    private void drawColumnBorder(Graphics graphics) {
        if (this.getColumn() == 3) {
            DrawingHelper.drawRightBorder(graphics, this.getWidth(), this.getHeight());
        } else if (this.getColumn() == 4) {
            DrawingHelper.drawLeftBorder(graphics, this.getHeight());
        }
    }

    private void setBackgroundColor(boolean isEditable, Graphics graphics) {
        graphics.setColor( isEditable ? Color.white : Color.black );
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void drawCentralNumber(boolean editableContent, Content content, Graphics graphics) {
        graphics.setColor( editableContent ? Color.black : Color.white);
        DrawingHelper.drawCentralChar(
                (content.getValue() == null) ? null : content,
                graphics, this.getWidth(),
                this.getHeight()
        );
    }

}
