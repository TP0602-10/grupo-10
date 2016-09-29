package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.MARGIN_CELL;

public class DisabledCellView extends CellView {

    private Object value;

    public DisabledCellView() {
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
    }

    @Override
    public void paintComponent(Graphics graphics) {

        Content content = null;
        Cell cell = (Cell)this.value;

        content = cell.getContent("Tag");
        if(content != null){
            setText(content.getValue().toString());
        }

        super.paintComponent(graphics);

        content = cell.getContent("CompareToRight");
        if(content != null){
            graphics.drawLine(this.getWidth(), this.getHeight(), 0, 0);
            graphics.drawString(content.getValue().toString(), (this.getWidth()/2)+ MARGIN_CELL, this.getHeight() / 2);
        }

        content = cell.getContent("CompareToDown");
        if(content != null){
            graphics.drawLine(this.getWidth(), this.getHeight(), 0, 0);
            graphics.drawString(content.getValue().toString(), 6, (this.getHeight() / 2) + + MARGIN_CELL);
        }
    }

}
