package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.disabled;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.MARGIN_CELL;

public class SudokuDisabledCellView extends DisabledCellView {

    public SudokuDisabledCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Cell cell = (Cell) getValue();
        setEnabled(false);

        if (cell instanceof MutableCell) {
            graphics.setColor(Color.BLUE);
        } else {
            graphics.setColor(Color.black);
        }

        Content content = cell.getContent("Tag");
        if (content != null) {
            String stringValue = content.getValue() != null ? content.getValue().toString() : "";
            graphics.drawString(stringValue, (this.getWidth() / 2) - 5, (this.getHeight() / 2) + 5);
        }
    }

}
