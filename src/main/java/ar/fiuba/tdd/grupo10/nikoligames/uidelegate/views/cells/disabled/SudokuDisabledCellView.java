package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.disabled;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;

import java.awt.*;

public class SudokuDisabledCellView extends DisabledCellView {

    public SudokuDisabledCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Cell cell = (Cell) getValue();

        Content content = cell.getContent("Tag");
        if (content != null) {
            setText(content.getValue().toString());
        }
        super.paintComponent(graphics);
    }

}
