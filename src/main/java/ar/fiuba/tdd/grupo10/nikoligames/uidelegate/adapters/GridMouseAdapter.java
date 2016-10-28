package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.GridView;

import java.awt.event.MouseAdapter;
import javax.swing.*;

public class GridMouseAdapter extends MouseAdapter {

    private GridView gridView;
    private GameEnum gameEnum;

    public GridMouseAdapter(GridView gridView, GameEnum gameEnum) {
        this.gridView = gridView;
        this.gameEnum = gameEnum;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = gridView.rowAtPoint(evt.getPoint());
        int col = gridView.columnAtPoint(evt.getPoint());
        Cell cell = (Cell) gridView.getModel().getValueAt(row, col);
        if (cell.isContentEditable() && row >= 0 && col >= 0) {
            Object newValue;
            if (SwingUtilities.isLeftMouseButton(evt)) {
                Object currentValue = getCurrentValue(cell, gameEnum);
                newValue = gameEnum.getNextPossibleValue(currentValue);
                updateValue(newValue, row, col);
            } else if (SwingUtilities.isRightMouseButton(evt)) {
                newValue = gameEnum.getPrevPossibleValue(cell.getValue(gameEnum.getMutableContentTag()));
                updateValue(newValue, row, col);
            }

        }
    }

    private Object getCurrentValue(Cell cell, GameEnum gameEnum) {
        return cell.getValue(gameEnum.getMutableContentTag());
    }

    private void updateValue(Object newValue, int row, int col) {
        gridView.getModel().setValueAt(newValue, row, col);
        gridView.repaint();
    }
}
