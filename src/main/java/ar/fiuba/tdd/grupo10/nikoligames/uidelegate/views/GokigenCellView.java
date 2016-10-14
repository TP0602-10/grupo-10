package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

import java.awt.*;

class GokigenCellView extends DisabledCellView {

    private Object value;

    GokigenCellView() {
        super();
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Cell cell = (Cell) this.value;
        String cellValue = String.valueOf(cell.getValue());
        graphics.drawOval(-4, -4, 20, 20);
        graphics.drawString(cellValue, 0, 10);
        if ("1".equals(cellValue)) {
            graphics.drawLine(this.getWidth(), 0, 0, this.getHeight());
        } else if ("2".equals(cellValue)){
            graphics.drawLine(0, 0, this.getWidth(), this.getHeight());
        }
    }

}
