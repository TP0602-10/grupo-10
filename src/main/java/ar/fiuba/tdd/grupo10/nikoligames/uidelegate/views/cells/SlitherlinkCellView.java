package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.*;

public class SlitherlinkCellView extends CellView {

    public SlitherlinkCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {

        Cell cell = (Cell)getValue();

        String value = (cell.getValue() != null) ? String.valueOf(cell.getValue()) : "";

        if (!value.isEmpty() ) {
            graphics.drawString(value, (this.getWidth() / 2) - 4, (this.getHeight() / 2) + 4);
        }

        graphics.fillOval(0,0,1,5);
        graphics.fillOval(this.getY(),0,5,5);
        graphics.fillOval(0,this.getX(),5,5);
        graphics.fillOval(this.getX(),0,5,5);

        drawBorder(value, graphics);
    }

    private void drawBorder(String cellValue, Graphics graphics) {

        if (UPPER_BORDER.equals(cellValue)) {
            graphics.fillRect(0, 0, this.getWidth(), 3);
        } else if (BOTTOM_BORDER.equals(cellValue)) {
            graphics.fillRect(0, this.getHeight() - 3, this.getWidth(), this.getHeight());
        } else if (LEFT_BORDER.equals(cellValue)) {
            graphics.fillRect(0, 0, 3, this.getHeight());
        } else if (RIGHT_BORDER.equals(cellValue)) {
            graphics.fillRect(this.getWidth() - 3, 0, 3, this.getHeight());
        }
    }

}
