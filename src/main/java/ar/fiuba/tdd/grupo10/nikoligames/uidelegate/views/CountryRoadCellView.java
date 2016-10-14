package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.MARGIN_CELL;

class CountryRoadCellView extends DisabledCellView {

    private Object value;

    CountryRoadCellView() {
        super();
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Cell cell = (Cell) this.value;
        String cellValue = String.valueOf(cell.getValue());
        graphics.drawString(cellValue, (this.getWidth() / 2) - 4, (this.getHeight() / 2) + 4);
        if ("1".equals(cellValue)) {
            //vertical
            graphics.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        } else if ("2".equals(cellValue)){
            //horizontal
            graphics.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        } else if ("3".equals(cellValue)) {
            //upper-left
            graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, 0);
            graphics.drawLine(0, this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
        } else if ("4".equals(cellValue)) {
            //bottom-left
            graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, this.getHeight());
            graphics.drawLine(0, this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
        } else if ("5".equals(cellValue)) {
            //upper-right
            graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, 0);
            graphics.drawLine(this.getWidth(), this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
        } else if ("6".equals(cellValue)) {
            //bottom-right
            graphics.drawLine(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, this.getHeight());
            graphics.drawLine(this.getWidth(), this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2);
        }
        graphics.fillRect(0, 0, 3, this.getHeight());
        graphics.fillRect(this.getWidth()-3, 0, 3, this.getHeight());
        graphics.fillRect(0, 0, this.getWidth(), 3);
        graphics.fillRect(0, this.getHeight()-3, this.getWidth(), this.getHeight());
    }

}
