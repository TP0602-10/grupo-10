package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.adapters.GridAdapter;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.cells.CellViewFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import static ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.ViewConstants.ROW_HEIGHT_DEFAULT;

public class GridView extends JTable {

    private GameEnum gameEnum;
    private GridAdapter adapter;

    public GridView(GridAdapter adapter, GameEnum gameEnum) {
        super(adapter);
        this.adapter = adapter;
        this.gameEnum = gameEnum;
        this.setRowHeight(ROW_HEIGHT_DEFAULT);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setGridColor(Color.red);
        this.addMouseListener(createMouseAdapter());
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return CellViewFactory.createCellView(gameEnum);
    }


    private MouseAdapter createMouseAdapter() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = GridView.this.rowAtPoint(evt.getPoint());
                int col = GridView.this.columnAtPoint(evt.getPoint());
                Cell cell = (Cell) GridView.this.getModel().getValueAt(row, col);
                if (cell.isContentEditable() && row >= 0 && col >= 0) {
                    Object newValue;
                    if (SwingUtilities.isLeftMouseButton(evt)) {
                        newValue = gameEnum.getNextPossibleValue(cell.getValue(gameEnum.getMutableContentTag()));
                        updateValue(newValue, row, col);
                    } else if (SwingUtilities.isRightMouseButton(evt)) {
                        newValue = gameEnum.getPrevPossibleValue(cell.getValue(gameEnum.getMutableContentTag()));
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
