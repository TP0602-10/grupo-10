package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.helpers.DrawingHelper;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.LETTER;
import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.SELECT;

public class WordSearchCellView extends CellView {

    public WordSearchCellView() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Cell cell = (Cell) getValue();
        drawBackground(cell, graphics);
        drawLetter(cell, graphics);
    }

    private void drawLetter(Cell cell, Graphics graphics) {
        Content content = cell.getContent(LETTER);
        graphics.setColor(Color.black);
        DrawingHelper.drawCentralChar(content, graphics, this.getWidth(), this.getHeight());
    }

    private void drawBackground(Cell cell, Graphics graphics) {
        String select = (String) cell.getValue(SELECT);
        if (StringUtils.isEmpty(select)) {
            graphics.setColor(Color.pink);
        } else {
            graphics.setColor(Color.ORANGE);
        }
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

}
