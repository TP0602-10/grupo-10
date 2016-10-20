package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.*;

import java.awt.*;

public class SlitherlinkCellView extends CellView {

    public SlitherlinkCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {

        Cell cell = (Cell)getValue();

        drawNumber(cell, graphics);

        setBorders(cell);
        drawBorders(graphics);

        graphics.fillOval(0,0,1,5);
        graphics.fillOval(this.getY(),0,5,5);
        graphics.fillOval(0,this.getX(),5,5);
        graphics.fillOval(this.getX(),0,5,5);
    }

    protected void drawNumber(Cell cell, Graphics graphics) {

        String number = getTextValue(cell.getValue());
        graphics.drawString(number, (this.getWidth() / 2) - 4, (this.getHeight() / 2) + 4);
    }
}
