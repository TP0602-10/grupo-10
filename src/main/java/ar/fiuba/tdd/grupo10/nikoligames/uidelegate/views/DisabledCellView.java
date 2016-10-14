package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.MARGIN_CELL;

class DisabledCellView extends CellView {

    private Object value;

    DisabledCellView() {
        super();
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    protected void setCustomRender(Object value) {

        super.setCustomRender(value);

        setValue(value);
        setEnabled(false);
        setBackground(Color.lightGray);
        setDisabledTextColor(Color.black);
    }

    @Override
    public void paintComponent(Graphics graphics) {

        Cell cell = (Cell) this.value;

        Content content = cell.getContent("Tag");
        if (content != null) {
            setText(content.getValue().toString());
        }

        super.paintComponent(graphics);

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
