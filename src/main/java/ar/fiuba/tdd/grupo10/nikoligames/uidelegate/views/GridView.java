package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridAdapter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.ROW_HEIGHT_DEFAULT;

public class GridView extends JTable {

    public GridView(GridAdapter grid) {
        super(grid);
        this.setRowHeight(ROW_HEIGHT_DEFAULT);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setGridColor(Color.red);
        this.addMouseListener(createMouseAdapter());
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {

        if (this.getModel().isCellEditable(row, column)) {
            return new EnabledCellView();
        }

        return new DisabledCellView();
    }

    private MouseAdapter createMouseAdapter() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = GridView.this.rowAtPoint(evt.getPoint());
                int col = GridView.this.columnAtPoint(evt.getPoint());
                Object currentValue = GridView.this.getModel().getValueAt(row, col);
                boolean editableCell = GridView.this.getModel().isCellEditable(row, col);
                if (editableCell && row >= 0 && col >= 0) {
                    Object newValue;
                    if (SwingUtilities.isLeftMouseButton(evt)) {
                        newValue = GameEnum.SUDOKU.getNextValue(currentValue);
                        updateValue(newValue, row, col);
                    } else if (SwingUtilities.isRightMouseButton(evt)) {
                        newValue = GameEnum.SUDOKU.getPrevValue(currentValue);
                        updateValue(newValue, row, col);
                    }

                }
            }

            private void updateValue(Object newValue, int row, int col) {
                GridView.this.getModel().setValueAt(newValue, row, col);
                GridView.this.repaint();
            }

        };
    }

}
